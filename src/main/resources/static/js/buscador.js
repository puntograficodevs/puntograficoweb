document.addEventListener("DOMContentLoaded", function () {
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-title]'));
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl);
  });

  const botonesEditar = document.querySelectorAll(".editar-btn");
  botonesEditar.forEach(boton => {
    boton.addEventListener("click", function () {
      const idOrden = this.getAttribute("data-idorden");
      const tipoProducto = this.getAttribute("data-tipoproducto");
      renderizarEdicionCorrespondiente(idOrden, tipoProducto);
    });
  });

  function renderizarEdicionCorrespondiente(idOrden, tipoProducto) {
    switch (tipoProducto) {
      case "agenda":
        window.location.href = `/crear-odt-agenda/${idOrden}`;
        break;
      case "anotador":
        window.location.href = `/crear-odt-anotador/${idOrden}`;
        break;
      default:
        console.warn("Tipo de producto no manejado:", tipoProducto);
    }
  }
});
