document.addEventListener('DOMContentLoaded', function () {
  // Valores predefinidos
  const recargoFactura = 0.21; // 21% sobre subtotal
  const recargoCredito = 0.10; // 10% sobre subtotal+impuesto

  // Inputs y checkboxes
  const adicionalCheckbox = document.getElementById('otro-con-adicional-disenio');
  const necesitaFacturaCheckbox = document.getElementById('necesitaFactura');
  const precioProductoInput = document.getElementById('precioProducto');
  const precioDisenioInput = document.getElementById('precioDisenio');
  const precioImpuestosInput = document.getElementById('precioImpuestos');
  const totalInput = document.getElementById('total');
  const abonadoInput = document.getElementById('abonado');
  const restaInput = document.getElementById('resta');
  const radiosMedioPago = document.querySelectorAll('input[name="medioPago.id"]');

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

  function calcularPrecio() {
    const precioProducto = parseInt(precioProductoInput.value, 10) || 0;
    let precioDisenioActual = parseInt(precioDisenioInput.value, 10) || 0;
    if (!adicionalCheckbox.checked) {
      precioDisenioActual = 0;
    }

    // Subtotal = producto + diseño
    let subtotal = Math.ceil(precioProducto + precioDisenioActual);

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
    precioDisenioInput.value = precioDisenioActual;
    precioImpuestosInput.value = impuestoFactura + recargoCreditoMonto;
    totalInput.value = total;
    restaInput.value = resta;
  }

  // Escuchamos cambios en todos los inputs
  precioProductoInput.addEventListener('input', calcularPrecio);
  precioDisenioInput.addEventListener('input', calcularPrecio);
  adicionalCheckbox.addEventListener('change', calcularPrecio);
  necesitaFacturaCheckbox.addEventListener('change', calcularPrecio);
  abonadoInput.addEventListener('input', calcularPrecio);
  radiosMedioPago.forEach(radio => radio.addEventListener('change', calcularPrecio));

  // Llamamos al inicio para inicializar los valores
  calcularPrecio();
});
