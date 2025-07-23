document.addEventListener('DOMContentLoaded', () => {
    const cantidadInput = document.getElementById('agenda-cantidad-hojas');
    const precioInput = document.getElementById('agenda-precio');
    const totalInput = document.getElementById('total');
    const abonadoInput = document.getElementById('abonado');
    const restaInput = document.getElementById('resta');
    const toggleFechaMuestra = document.getElementById('toggleFechaMuestra');
    const fechaRow = document.getElementById('fechaMuestraRow');
    const adicionalCheckbox = document.getElementById('agenda-con-adicional-disenio');
    const tapaInputRow = document.getElementById('inputTapaPersonalizadaRow');

    // Inputs dinámicos tipoTapa y tipoColor (radios)
    const tapaRadios = document.querySelectorAll('input[name="tipoTapaAgenda.id"]');
    const colorRadios = document.querySelectorAll('input[name="tipoColorAgenda.id"]');

    let tipoTapaSeleccionada = null;
    let tipoColorSeleccionado = null;

    tapaRadios.forEach(radio => {
        radio.addEventListener('change', () => {
            tipoTapaSeleccionada = radio.value;
            const esOtra = radio.labels[0]?.textContent.trim().toLowerCase() === 'otra';
            tapaInputRow.classList.toggle('d-none', !esOtra);
            actualizarPrecio();
        });
    });

    colorRadios.forEach(radio => {
        radio.addEventListener('change', () => {
            tipoColorSeleccionado = radio.value;
            actualizarPrecio();
        });
    });

    async function actualizarPrecio() {
        const cantidad = cantidadInput.value;
        const tipoTapaId = tipoTapaSeleccionada;
        const tipoColorId = tipoColorSeleccionado;

        if (!cantidad || !tipoTapaId || !tipoColorId) {
            precioInput.value = '';
            totalInput.value = '';
            return;
        }

        try {
            const response = await fetch(`/api/plantilla-agenda/precio?cantidadHojas=${cantidad}&tipoTapaId=${tipoTapaId}&tipoColorId=${tipoColorId}`);
            if (response.ok) {
                let precio = await response.json();

                if (adicionalCheckbox.checked) {
                    precio += 5000;
                }

                precioInput.value = precio;

                // Solo actualizar el total si está vacío o coincide con el precio anterior
                if (!totalInput.value || parseFloat(totalInput.value) === parseFloat(precio)) {
                    totalInput.value = precio;
                }

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

    function actualizarResta() {
        const total = parseFloat(totalInput.value) || 0;
        const abonado = parseFloat(abonadoInput.value) || 0;
        const resta = total - abonado;

        restaInput.value = Math.abs(resta);
        restaInput.style.color = resta < 0 ? 'red' : 'black';
        if (resta < 0) {
            restaInput.value = '-' + restaInput.value;
        }
    }

    function bindearInputFechaMuestra() {
        fechaRow.classList.toggle('d-none', !this.checked);
    }

    // Listeners
    cantidadInput.addEventListener('input', actualizarPrecio);
    adicionalCheckbox.addEventListener('change', actualizarPrecio);
    abonadoInput.addEventListener('input', actualizarResta);
    toggleFechaMuestra.addEventListener('change', bindearInputFechaMuestra);

    // Inicializar con valores actuales
    tapaRadios.forEach(radio => {
        if (radio.checked) radio.dispatchEvent(new Event('change'));
    });
    colorRadios.forEach(radio => {
        if (radio.checked) radio.dispatchEvent(new Event('change'));
    });

    actualizarResta();
});
