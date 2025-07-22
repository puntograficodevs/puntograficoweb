document.addEventListener('DOMContentLoaded', () => {
  const selectTipoTapa = document.getElementById('agenda-tipo-tapa');
  const divPersonalizada = document.querySelector('.mb-3.d-none, .mb-3.d-block');
  const cantidadHojas = document.getElementById('agenda-cantidad-hojas');
  const tipoColor = document.getElementById('agenda-tipo-color');
  const precioInput = document.getElementById('agenda-precio');
  const adicionalCheck = document.getElementById('agenda-con-adicional-disenio');

  // Mostrar/ocultar campo tipo tapa personalizada
  selectTipoTapa.addEventListener('change', () => {
    const opcion = selectTipoTapa.options[selectTipoTapa.selectedIndex].text.trim().toUpperCase();

    if (opcion === 'OTRA') {
      divPersonalizada.classList.remove('d-none');
      divPersonalizada.classList.add('d-block');
    } else {
      divPersonalizada.classList.remove('d-block');
      divPersonalizada.classList.add('d-none');
    }

    buscarYSetearPrecio();
  });

  cantidadHojas.addEventListener('input', buscarYSetearPrecio);
  tipoColor.addEventListener('change', buscarYSetearPrecio);
  adicionalCheck.addEventListener('change', buscarYSetearPrecio);

  async function buscarYSetearPrecio() {
    const hojas = parseInt(cantidadHojas.value);
    const tapaId = selectTipoTapa.value;
    const colorId = tipoColor.value;
    const conAdicional = adicionalCheck.checked;

    if (!hojas || hojas <= 0 || !tapaId || !colorId) {
      precioInput.value = '';
      return;
    }

    try {
      const response = await fetch(`/api/plantilla-agenda/precio?cantidadHojas=${hojas}&tipoTapaId=${tapaId}&tipoColorId=${colorId}`);

      if (response.ok) {
        let precio = parseInt(await response.text()) || 0;
        if (conAdicional) precio += 5000;
        precioInput.value = precio;
      } else if (response.status === 204) { // No existe precio para esa combinación
        precioInput.value = '';
      } else {
        alert('Error al obtener el precio');
      }
    } catch (error) {
      alert('Error en la conexión: ' + error);
    }
  }
});
