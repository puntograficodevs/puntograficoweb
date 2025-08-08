document.addEventListener("DOMContentLoaded", () => {
    const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
    const fechaMuestraRow = document.getElementById("fechaMuestraRow");

    const medidaRadios = document.querySelectorAll(".medida-radio");
    const medidaPersonalizadaGroup = document.getElementById("medidaPersonalizadaGroup");

    const tipoTapaRadios = document.querySelectorAll(".tipo-tapa-radio");
    const tipoTapaPersonalizadaGroup = document.getElementById("tipoTapaPersonalizadaGroup");

    const cantidadHojasInput = document.getElementById("cuaderno-anillado-cantidad-hojas");

    const totalInput = document.getElementById("total");
    const abonadoInput = document.getElementById("abonado");
    const restaInput = document.getElementById("resta");

    const adicionalDisenioCheckbox = document.getElementById("cuaderno-anillado-con-adicional-disenio");
    const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

    let precioBase = null; // Precio de plantilla si existe
    let totalActual = 0;
    let adicionalAplicado = false;
    let creditoAplicado = false;

    // Mostrar/ocultar fecha de muestra
    toggleFechaMuestra.addEventListener("change", () => {
        fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
    });

    // Mostrar input de medida personalizada
    medidaRadios.forEach(radio => {
        radio.addEventListener("change", () => {
            medidaPersonalizadaGroup.classList.toggle(
                "d-none",
                radio.dataset.medida !== "OTRA"
            );
            buscarPrecio();
        });
    });

    // Mostrar input de tipo de tapa personalizada
    tipoTapaRadios.forEach(radio => {
        radio.addEventListener("change", () => {
            tipoTapaPersonalizadaGroup.classList.toggle(
                "d-none",
                radio.dataset.tipoTapa !== "OTRA"
            );
            buscarPrecio();
        });
    });

    // Buscar precio al cambiar cantidad de hojas
    cantidadHojasInput.addEventListener("input", buscarPrecio);

    // Calcular resta al cambiar abonado o total manual
    abonadoInput.addEventListener("input", actualizarResta);
    totalInput.addEventListener("input", () => {
        precioBase = null; // Total manual rompe vínculo con precio de plantilla
        totalActual = parseFloat(totalInput.value) || 0;
        actualizarResta();
    });

    // Adicional diseño (sumar o restar según estado)
    adicionalDisenioCheckbox.addEventListener("change", () => {
        if (adicionalDisenioCheckbox.checked && !adicionalAplicado) {
            totalActual += 5000;
            adicionalAplicado = true;
        } else if (!adicionalDisenioCheckbox.checked && adicionalAplicado) {
            totalActual -= 5000;
            adicionalAplicado = false;
        }
        actualizarTotal();
    });

    // Medio de pago (sumar o restar 10% según sea Crédito)
    medioPagoRadios.forEach(radio => {
        radio.addEventListener("change", () => {
            const esCredito = radio.nextElementSibling.textContent.trim().toUpperCase() === "CRÉDITO";

            if (esCredito && !creditoAplicado) {
                totalActual *= 1.10;
                creditoAplicado = true;
            } else if (!esCredito && creditoAplicado) {
                totalActual /= 1.10;
                creditoAplicado = false;
            }
            actualizarTotal();
        });
    });

    // ---- Funciones ----
    function buscarPrecio() {
        const medidaId = getCheckedValue(medidaRadios);
        const tipoTapaId = getCheckedValue(tipoTapaRadios);
        const cantidadHojas = cantidadHojasInput.value;

        if (medidaId && tipoTapaId && cantidadHojas) {
            fetch(`/api/plantilla-cuaderno-anillado/precio?medidaCuadernoAnilladoId=${medidaId}&tipoTapaCuadernoAnilladoId=${tipoTapaId}&cantidadHojas=${cantidadHojas}`)
                .then(res => res.ok ? res.json() : null)
                .then(data => {
                    if (data) {
                        precioBase = parseFloat(data);
                        totalActual = precioBase;
                        adicionalAplicado = false;
                        creditoAplicado = false;
                        adicionalDisenioCheckbox.checked = false;
                        medioPagoRadios.forEach(r => r.checked = false);
                        actualizarTotal();
                    }
                });
        }
    }

    function actualizarTotal() {
        totalInput.value = Math.round(totalActual);
        actualizarResta();
    }

    function actualizarResta() {
        const abonado = parseFloat(abonadoInput.value) || 0;
        restaInput.value = Math.round((parseFloat(totalInput.value) || 0) - abonado);
    }

    function getCheckedValue(radios) {
        const checked = Array.from(radios).find(r => r.checked);
        return checked ? checked.value : null;
    }
});
