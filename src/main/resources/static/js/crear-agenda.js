document.addEventListener('DOMContentLoaded', () => {
    const cantidadInput = document.getElementById('agenda-cantidad-hojas');
    const tipoTapaSelect = document.getElementById('agenda-tipo-tapa');
    const tipoColorSelect = document.getElementById('agenda-tipo-color');
    const precioInput = document.getElementById('agenda-precio');
    const totalInput = document.getElementById('total');
    const abonadoInput = document.getElementById('abonado');
    const restaInput = document.getElementById('resta');

    const adicionalCheckbox = document.getElementById('agenda-con-adicional-disenio');
    totalInput.readOnly = true;

    async function actualizarPrecio() {
        const cantidad = cantidadInput.value;
        const tipoTapaId = tipoTapaSelect.value;
        const tipoColorId = tipoColorSelect.value;

        if (!cantidad || !tipoTapaId || !tipoColorId) {
            precioInput.value = '';
            totalInput.value = '';
            return;
        }

        try {
            const response = await fetch(`/api/plantilla-agenda/precio?cantidadHojas=${cantidad}&tipoTapaId=${tipoTapaId}&tipoColorId=${tipoColorId}`);
            if (response.ok) {
                let precio = await response.json();

                // Si está marcado el adicional sumamos 5000
                if(adicionalCheckbox.checked) {
                    precio += 5000;
                }

                precioInput.value = precio;
                totalInput.value = precio;

                actualizarResta();
            } else if (response.status === 204) {
                precioInput.value = '';
                totalInput.value = '';
                actualizarResta();
            } else {
                console.error('Error al obtener precio');
            }
        } catch (error) {
            console.error('Error en la conexión:', error);
        }
    }

    // Agregamos listener al checkbox para que actualice el precio cuando cambie
    adicionalCheckbox.addEventListener('change', actualizarPrecio);

    function actualizarResta() {
        const total = parseFloat(totalInput.value) || 0;
        const abonado = parseFloat(abonadoInput.value) || 0;
        const resta = total - abonado;

        // Mostrar resultado y poner signo rojo si resta negativa
        restaInput.value = Math.abs(resta);
        if (resta < 0) {
            restaInput.style.color = 'red';
            restaInput.value = '-' + restaInput.value;
        } else {
            restaInput.style.color = 'black';
        }
    }

    // Listeners
    cantidadInput.addEventListener('input', actualizarPrecio);
    tipoTapaSelect.addEventListener('change', actualizarPrecio);
    tipoColorSelect.addEventListener('change', actualizarPrecio);

    abonadoInput.addEventListener('input', actualizarResta);

    // Inicializo por si ya hay datos
    actualizarPrecio();
    actualizarResta();
});