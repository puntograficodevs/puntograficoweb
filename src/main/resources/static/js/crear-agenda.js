document.addEventListener('DOMContentLoaded', () => {
    const cantidadHojasInput = document.getElementById('agenda-cantidad-hojas');
    const cantidadAgendasInput = document.getElementById('agenda-cantidad');
    const totalInput = document.getElementById('total');
    const abonadoInput = document.getElementById('abonado');
    const restaInput = document.getElementById('resta');
    const toggleFechaMuestra = document.getElementById('toggleFechaMuestra');
    const fechaRow = document.getElementById('fechaMuestraRow');
    const adicionalCheckbox = document.getElementById('agenda-con-adicional-disenio');
    const tapaInputRow = document.getElementById('inputTapaPersonalizadaRow');

    const tapaRadios = document.querySelectorAll('input[name="tipoTapaAgenda.id"]');
    const colorRadios = document.querySelectorAll('input[name="tipoColorAgenda.id"]');
    const medioPagoRadios = document.querySelectorAll('input[name="medioPago.id"]');

    let tipoTapaSeleccionada = null;
    let tipoColorSeleccionado = null;
    let esCredito = false;
    let totalManualBase = null; // Base original escrita por el usuario
    let totalCalculadoBase = null; // Base traída del backend

    // Inicialización por defecto
    if (!cantidadAgendasInput.value) cantidadAgendasInput.value = 1;
    if (!abonadoInput.value) abonadoInput.value = 0;

    tapaRadios.forEach(radio => {
        radio.addEventListener('change', () => {
            tipoTapaSeleccionada = radio.value;
            const label = document.querySelector(`label[for="${radio.id}"]`);
            const esOtra = label?.textContent.trim().toLowerCase() === 'otra';
            tapaInputRow.classList.toggle('d-none', !esOtra);
            actualizarTotal();
        });
    });

    colorRadios.forEach(radio => {
        radio.addEventListener('change', () => {
            tipoColorSeleccionado = radio.value;
            actualizarTotal();
        });
    });

    medioPagoRadios.forEach(radio => {
        radio.addEventListener('change', () => {
            const label = document.querySelector(`label[for="${radio.id}"]`);
            const texto = label?.textContent.trim().toUpperCase();
            const nuevoEsCredito = texto === 'CRÉDITO';

            const base = totalManualBase ?? totalCalculadoBase;

            if (base != null) {
                let nuevoTotal = base;

                if (adicionalCheckbox.checked) nuevoTotal += 5000;
                if (nuevoEsCredito) nuevoTotal *= 1.1;

                totalInput.value = Math.round(nuevoTotal);
            }

            esCredito = nuevoEsCredito;
            actualizarResta();
        });
    });

    adicionalCheckbox.addEventListener('change', () => {
        const base = totalManualBase ?? totalCalculadoBase;

        if (base != null) {
            let nuevoTotal = base;

            if (adicionalCheckbox.checked) nuevoTotal += 5000;
            if (esCredito) nuevoTotal *= 1.1;

            totalInput.value = Math.round(nuevoTotal);
            actualizarResta();
        }
    });

    async function actualizarTotal() {
        const cantidadHojas = cantidadHojasInput.value;
        const cantidadAgendas = parseInt(cantidadAgendasInput.value) || 1;
        const tipoTapaId = tipoTapaSeleccionada;
        const tipoColorId = tipoColorSeleccionado;

        if (!cantidadHojas || !tipoTapaId || !tipoColorId) {
            totalInput.value = '';
            return;
        }

        try {
            const response = await fetch(`/api/plantilla-agenda/precio?cantidadHojas=${cantidadHojas}&tipoTapaId=${tipoTapaId}&tipoColorId=${tipoColorId}`);
            if (response.ok) {
                let precioUnitario = await response.json();

                let totalBase = precioUnitario * cantidadAgendas;
                totalCalculadoBase = totalBase;
                totalManualBase = null;

                let nuevoTotal = totalBase;

                if (adicionalCheckbox.checked) nuevoTotal += 5000;
                if (esCredito) nuevoTotal *= 1.1;

                totalInput.value = Math.round(nuevoTotal);
                actualizarResta();
            } else if (response.status === 204) {
                totalInput.value = '';
                actualizarResta();
            } else {
                console.error('Error al obtener precio base');
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

    // Detectar escritura manual de total
    totalInput.addEventListener('input', () => {
        const base = parseFloat(totalInput.value);
        if (!isNaN(base)) {
            totalManualBase = base;
            totalCalculadoBase = null;
            actualizarDesdeBaseManual();
        }
    });

    function actualizarDesdeBaseManual() {
        if (totalManualBase != null) {
            let nuevoTotal = totalManualBase;

            if (adicionalCheckbox.checked) nuevoTotal += 5000;
            if (esCredito) nuevoTotal *= 1.1;

            totalInput.value = Math.round(nuevoTotal);
            actualizarResta();
        }
    }

    // Bindings generales
    cantidadHojasInput.addEventListener('input', actualizarTotal);
    cantidadAgendasInput.addEventListener('input', actualizarTotal);
    abonadoInput.addEventListener('input', actualizarResta);
    toggleFechaMuestra.addEventListener('change', bindearInputFechaMuestra);

    // Inicializar radios marcados
    tapaRadios.forEach(radio => {
        if (radio.checked) radio.dispatchEvent(new Event('change'));
    });
    colorRadios.forEach(radio => {
        if (radio.checked) radio.dispatchEvent(new Event('change'));
    });
    medioPagoRadios.forEach(radio => {
        if (radio.checked) radio.dispatchEvent(new Event('change'));
    });

    actualizarResta();
});
