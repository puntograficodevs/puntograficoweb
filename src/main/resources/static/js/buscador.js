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
      case "carpeta con solapas":
        window.location.href = `/crear-odt-carpeta-solapa/${idOrden}`;
        break;
      case "catálogo":
        window.location.href = `/crear-odt-catalogo/${idOrden}`;
        break;
      case "cierra bolsas":
        window.location.href = `/crear-odt-cierra-bolsas/${idOrden}`;
        break;
      case "combo":
        window.location.href = `/crear-odt-combo/${idOrden}`;
        break;
      case "cuaderno anillado":
        window.location.href = `/crear-odt-cuaderno-anillado/${idOrden}`;
        break;
      case "entrada":
        window.location.href = `/crear-odt-entrada/${idOrden}`;
        break;
      case "etiqueta":
        window.location.href = `/crear-odt-etiqueta/${idOrden}`;
        break;
      default:
        console.warn("Tipo de producto no manejado:", tipoProducto);
    }
  }
});
