document.addEventListener('DOMContentLoaded', () => {
      // Valores predefinidos
      const precioDisenio = 5000;
      const recargoFactura = 0.21; // 21% sobre subtotal
      const recargoCredito = 0.10; // 10% sobre subtotal+impuesto

      // Inputs y checkboxes
      const adicionalCheckbox = document.getElementById('sublimacion-con-adicional-disenio');
      const necesitaFacturaCheckbox = document.getElementById('necesitaFactura');
      const precioProductoInput = document.getElementById('precioProducto');
      const precioDisenioInput = document.getElementById('precioDisenio');
      const precioImpuestosInput = document.getElementById('precioImpuestos');
      const totalInput = document.getElementById('total');
      const abonadoInput = document.getElementById('abonado');
      const restaInput = document.getElementById('resta');
      const radiosMedioPago = document.querySelectorAll('input[name="medioPago.id"]');
      const radiosMaterial = document.querySelectorAll('input[name="materialSublimacion.id"]');
      const radiosCantidad = document.querySelectorAll('input[name="cantidadSublimacion.id"]');
      const cantidadSublimacionesInput = document.getElementById('cantidad');

      // Inicializamos valores visibles
      cantidadSublimacionesInput.value = 1;
      precioDisenioInput.value = 0;
      precioImpuestosInput.value = 0;
      totalInput.value = 0;
      restaInput.value = 0;
      abonadoInput.value = 0;

      // Toggles
      const toggleFechaMuestra = document.getElementById('toggleFechaMuestra');
      const fechaMuestraRow = document.getElementById('fechaMuestraRow');
      toggleFechaMuestra.addEventListener('change', () => {
          fechaMuestraRow.classList.toggle('d-none', !toggleFechaMuestra.checked);
      });

      const cantidadInputRow = document.getElementById('cantidadPersonalizadaGroup');
      radiosCantidad.forEach(radio => {
        radio.addEventListener('change', () => {
          const label = document.querySelector(`label[for="${radio.id}"]`);
          const esOtra = label?.textContent.trim().toLowerCase() === 'otra';
          cantidadInputRow.classList.toggle('d-none', !esOtra);
        });
      });

      function resetearPrecio() {
        precioProductoInput.value = 0;
        precioProductoInput.readOnly = false;
      }

      async function calcularPrecio() {
        const materialSeleccionado = document.querySelector('input[name="materialSublimacion.id"]:checked');
        const cantidadSeleccionada = document.querySelector('input[name="cantidadSublimacion.id"]:checked');
        let materialSublimacionId = 0;
        let cantidadSublimacionId = 0;
        let precioProducto = 0;

        if (!materialSeleccionado || !cantidadSeleccionada) {
            return;
        } else {
            materialSublimacionId = Number(materialSeleccionado.value);
            cantidadSublimacionId = Number(cantidadSeleccionada.value);
        }

        try {
            const response = await fetch(`/api/plantilla-sublimacion/precio?materialSublimacionId=${materialSublimacionId}&cantidadSublimacionId=${cantidadSublimacionId}`);
            if (response.status === 200) {
                let precioUnitario = await response.json();
                if (materialSublimacionId === 1) {
                    precioProducto = precioUnitario;
                    precioProductoInput.readOnly = true;
                } else {
                    const cantidad = parseInt(cantidadSublimacionesInput.value, 10) || 0;
                    precioProducto = precioUnitario * cantidad;
                    precioProductoInput.readOnly = true;
                }

            } else if (response.status === 204) {
                precioProductoInput.readOnly = false;
                precioProducto = parseInt(precioProductoInput.value, 10) || 0;
            } else {
                console.error('Error al obtener precio base');
            }
        } catch (error) {
            console.error('Error en la conexión:', error);
        }

        const precioDisenioActual = adicionalCheckbox.checked && materialSublimacionId === 1 ? precioDisenio : 0;

        // Subtotal = producto + diseño
        let subtotal = precioProducto + precioDisenioActual;

        // Impuesto por factura
        let impuestoFactura = 0;
        if (necesitaFacturaCheckbox.checked) {
          impuestoFactura = Math.ceil(subtotal * recargoFactura);
        }

        // Total inicial con impuesto
        let total = subtotal + impuestoFactura;

        // Recargo por crédito
        const medioPagoSeleccionado = document.querySelector('input[name="medioPago.id"]:checked');
        let recargoCreditoMonto = 0;
        if (medioPagoSeleccionado && Number(medioPagoSeleccionado.value) === 2) {
          recargoCreditoMonto = Math.ceil(total * recargoCredito);
          total += recargoCreditoMonto;
        }

        // Cantidad abonada
        const abonado = parseInt(abonadoInput.value, 10) || 0;

        // Resta
        const resta = total - abonado;

        // Actualizamos inputs visibles
        precioDisenioInput.value = precioDisenioActual;
        precioImpuestosInput.value = impuestoFactura + recargoCreditoMonto;
        totalInput.value = total;
        restaInput.value = resta;
        precioProductoInput.value = precioProducto;
      }

      // Escuchamos cambios en todos los inputs
      precioProductoInput.addEventListener('input', calcularPrecio);
      cantidadSublimacionesInput.addEventListener('input', calcularPrecio);
      adicionalCheckbox.addEventListener('change', calcularPrecio);
      necesitaFacturaCheckbox.addEventListener('change', calcularPrecio);
      abonadoInput.addEventListener('input', calcularPrecio);
      radiosMedioPago.forEach(radio => radio.addEventListener('change', calcularPrecio));
      radiosMaterial.forEach(radio => radio.addEventListener('change', () => {
        resetearPrecio();
        calcularPrecio();
      }));
      radiosCantidad.forEach(radio => radio.addEventListener('change', () => {
        resetearPrecio();
        calcularPrecio();
      }));

      // Llamamos al inicio para inicializar los valores
      calcularPrecio();
});
