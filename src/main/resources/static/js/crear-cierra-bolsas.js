document.addEventListener('DOMContentLoaded', () => {
      // Valores predefinidos
      const precioDisenio = 2500;
      const recargoFactura = 0.21; // 21% sobre subtotal
      const recargoCredito = 0.10; // 10% sobre subtotal+impuesto

      // Inputs y checkboxes
      const adicionalCheckbox = document.getElementById('cierra-bolsas-con-adicional-disenio');
      const necesitaFacturaCheckbox = document.getElementById('necesitaFactura');
      const precioProductoInput = document.getElementById('precioProducto');
      const precioDisenioInput = document.getElementById('precioDisenio');
      const precioImpuestosInput = document.getElementById('precioImpuestos');
      const totalInput = document.getElementById('total');
      const abonadoInput = document.getElementById('abonado');
      const restaInput = document.getElementById('resta');
      const radiosMedioPago = document.querySelectorAll('input[name="medioPago.id"]');
      const radiosMedida = document.querySelectorAll('input[name="medidaCierraBolsas.id"]');
      const radiosCantidad = document.querySelectorAll('input[name="cantidadCierraBolsas.id"]');
      const radiosTroquelado = document.querySelectorAll('input[name="tipoTroqueladoCierraBolsas.id"]');


      // Inicializamos valores visibles
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

      const cantidadInputRow = document.getElementById('cantidadManualGroup');
      radiosCantidad.forEach(radio => {
        radio.addEventListener('change', () => {
          const label = document.querySelector(`label[for="${radio.id}"]`);
          const esOtra = label?.textContent.trim().toLowerCase() === 'otra';
          cantidadInputRow.classList.toggle('d-none', !esOtra);
        });
      });

      const medidaInputRow = document.getElementById('medidaPersonalizadaGroup');
      radiosMedida.forEach(radio => {
        radio.addEventListener('change', () => {
          const label = document.querySelector(`label[for="${radio.id}"]`);
          const esOtra = label?.textContent.trim().toLowerCase() === 'otra';
          medidaInputRow.classList.toggle('d-none', !esOtra);
        });
      });

      function resetearPrecio() {
        precioProductoInput.value = 0;
        precioProductoInput.readOnly = false;
      }

      async function calcularPrecio() {
        const medidaSeleccionada = document.querySelector('input[name="medidaCierraBolsas.id"]:checked');
        const cantidadSeleccionada = document.querySelector('input[name="cantidadCierraBolsas.id"]:checked');
        const troqueladoSeleccionado = document.querySelector('input[name="tipoTroqueladoCierraBolsas.id"]:checked');
        let medidaCierraBolsasId = 0;
        let cantidadCierraBolsasId = 0;
        let tipoTroqueladoCierraBolsasId = 0;
        let precioProducto = 0;

        if (!medidaSeleccionada || !cantidadSeleccionada || !troqueladoSeleccionado) {
            return;
        } else {
            tipoTroqueladoCierraBolsasId = Number(troqueladoSeleccionado.value);
            medidaCierraBolsasId = Number(medidaSeleccionada.value);
            cantidadCierraBolsasId = Number(cantidadSeleccionada.value);
        }

        try {
            const response = await fetch(`/api/plantilla-cierra-bolsas/precio?tipoTroqueladoCierraBolsasId=${tipoTroqueladoCierraBolsasId}&medidaCierraBolsasId=${medidaCierraBolsasId}&cantidadCierraBolsasId=${cantidadCierraBolsasId}`);
            if (response.status === 200) {
                precioProducto = await response.json();
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

        const precioDisenioActual = adicionalCheckbox.checked ? precioDisenio : 0;

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
      adicionalCheckbox.addEventListener('change', calcularPrecio);
      necesitaFacturaCheckbox.addEventListener('change', calcularPrecio);
      abonadoInput.addEventListener('input', calcularPrecio);
      radiosMedioPago.forEach(radio => radio.addEventListener('change', calcularPrecio));
      radiosTroquelado.forEach(radio => radio.addEventListener('change', () => {
        resetearPrecio();
        calcularPrecio();
      }));
      radiosMedida.forEach(radio => radio.addEventListener('change', () => {
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
