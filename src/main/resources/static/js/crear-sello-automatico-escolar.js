document.addEventListener('DOMContentLoaded', () => {
      // Valores predefinidos
      const recargoFactura = 0.21; // 21% sobre subtotal
      const recargoCredito = 0.10; // 10% sobre subtotal+impuesto

      // Inputs y checkboxes
      const necesitaFacturaCheckbox = document.getElementById('necesitaFactura');
      const precioProductoInput = document.getElementById('precioProducto');
      const precioImpuestosInput = document.getElementById('precioImpuestos');
      const totalInput = document.getElementById('total');
      const abonadoInput = document.getElementById('abonado');
      const restaInput = document.getElementById('resta');
      const radiosMedioPago = document.querySelectorAll('input[name="medioPago.id"]');
      const radiosModelo = document.querySelectorAll('input[name="modeloSelloAutomaticoEscolar.id"]');
      const cantidadSellosInput = document.getElementById('cantidad');

      // Inicializamos valores visibles
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

      function resetearPrecio() {
        precioProductoInput.value = 0;
        precioProductoInput.readOnly = false;
      }

      async function calcularPrecio() {
        const modeloSeleccionado = document.querySelector('input[name="modeloSelloAutomaticoEscolar.id"]:checked');
        let modeloSelloAutomaticoEscolarId = 0;
        let precioProducto = 0;

        if (!modeloSeleccionado) {
            return;
        } else {
            modeloSelloAutomaticoEscolarId = Number(modeloSeleccionado.value);
        }

        try {
            const response = await fetch(`/api/plantilla-sello-automatico-escolar/precio?modeloSelloAutomaticoEscolarId=${modeloSelloAutomaticoEscolarId}`);
            if (response.status === 200) {
                let precioUnitario = await response.json();
                const cantidad = parseInt(cantidadSellosInput.value) || 0;
                precioProducto = Math.ceil(precioUnitario * cantidad);
                precioProductoInput.readOnly = true;
            } else if (response.status === 204) {
                precioProductoInput.readOnly = false;
                precioProducto = parseInt(precioProductoInput.value, 10) || 0;
            } else {
                console.error('Error al obtener precio base');
            }
        } catch (error) {
            console.error('Error en la conexión:', error);
        }

        // Subtotal = producto + diseño
        let subtotal = precioProducto;

        // Impuesto por factura
        let impuestoFactura = 0;
        if (necesitaFacturaCheckbox.checked) {
          impuestoFactura = Math.ceil(subtotal * recargoFactura);
        }

        // Total inicial con impuesto
        let total = Math.ceil(subtotal + impuestoFactura);

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
        precioImpuestosInput.value = impuestoFactura + recargoCreditoMonto;
        totalInput.value = total;
        restaInput.value = resta;
        precioProductoInput.value = precioProducto;
      }

      // Escuchamos cambios en todos los inputs
      precioProductoInput.addEventListener('input', calcularPrecio);
      cantidadSellosInput.addEventListener('input', calcularPrecio);
      necesitaFacturaCheckbox.addEventListener('change', calcularPrecio);
      abonadoInput.addEventListener('input', calcularPrecio);
      radiosMedioPago.forEach(radio => radio.addEventListener('change', calcularPrecio));
      radiosModelo.forEach(radio => radio.addEventListener('change', () => {
        resetearPrecio();
        calcularPrecio();
      }));

      // Llamamos al inicio para inicializar los valores
      calcularPrecio();
});
