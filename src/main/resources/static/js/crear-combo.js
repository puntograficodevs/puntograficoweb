document.addEventListener("DOMContentLoaded", () => {
    const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
    const fechaMuestraRow = document.getElementById("fechaMuestraRow");

    const tipoRadios = document.querySelectorAll(".tipo-radio");
    const cantidadInput = document.getElementById("combo-cantidad");

    const totalInput = document.getElementById("total");
    const abonadoInput = document.getElementById("abonado");
    const restaInput = document.getElementById("resta");

    const adicionalDisenioCheckbox = document.getElementById("combo-con-adicional-disenio");
    const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

    let precioBasePlantilla = null; // Precio de la plantilla
    let totalActual = 0;
    let adicionalAplicado = false;
    let creditoAplicado = false;

    // Toggle fecha muestra
    toggleFechaMuestra.addEventListener("change", () => {
        fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
    });

    // Selección de tipo de combo → buscar precio
    tipoRadios.forEach(radio => {
        radio.addEventListener("change", () => {
            const tipoId = radio.value;
            if (tipoId) {
                fetch(`/api/plantilla-combo/precio?tipoComboId=${tipoId}`)
                    .then(res => res.ok ? res.json() : null)
                    .then(data => {
                        if (data) {
                            precioBasePlantilla = parseFloat(data);
                            recalcularDesdeBase();
                            // Reset extras
                            adicionalAplicado = false;
                            creditoAplicado = false;
                            adicionalDisenioCheckbox.checked = false;
                            medioPagoRadios.forEach(r => r.checked = false);
                        }
                    });
            }
        });
    });

    // Cambio de cantidad → recalcular
    cantidadInput.addEventListener("input", recalcularDesdeBase);

    // Adicional diseño
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

    // Medio de pago
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

    // Abonado → recalcular resta
    abonadoInput.addEventListener("input", actualizarResta);

    // ---------------- FUNCIONES ----------------
    function recalcularDesdeBase() {
        if (precioBasePlantilla !== null) {
            const cantidad = parseInt(cantidadInput.value) || 1;
            totalActual = precioBasePlantilla * cantidad;
            adicionalAplicado = false;
            creditoAplicado = false;
            adicionalDisenioCheckbox.checked = false;
            medioPagoRadios.forEach(r => r.checked = false);
            actualizarTotal();
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
});
