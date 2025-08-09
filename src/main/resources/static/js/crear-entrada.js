document.addEventListener("DOMContentLoaded", () => {
  // Elementos clave
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");

  const tipoPapelRadios = document.querySelectorAll("input[name='tipoPapelEntrada.id']");
  const tipoColorRadios = document.querySelectorAll("input[name='tipoColorEntrada.id']");
  const terminacionRadios = document.querySelectorAll("input[name='terminacionEntrada.id']");
  const medidaRadios = document.querySelectorAll("input[name='medidaEntrada.id']");
  const medidaPersonalizadaGroup = document.getElementById("medidaPersonalizadaGroup");
  const cantidadRadios = document.querySelectorAll("input[name='cantidadEntrada.id']");
  const cantidadPersonalizadaGroup = document.getElementById("cantidadPersonalizadaGroup");

  const numeradoRadios = document.querySelectorAll("input[name='numeradoEntrada.id']");
  const tipoTroqueladoRadios = document.querySelectorAll("input[name='tipoTroqueladoEntrada.id']");

  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");
  const adicionalDisenioCheckbox = document.getElementById("entrada-con-adicional-disenio");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

  // Variables internas
  let precioBase = null;
  let totalActual = 0;
  let adicionalAplicado = false;
  let creditoAplicado = false;

  // 1. Toggle fecha muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // 2. Mostrar input personalizada medida si eligieron OTRA
  medidaRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      medidaPersonalizadaGroup.classList.toggle(
        "d-none",
        radio.nextElementSibling.textContent.trim().toUpperCase() !== "OTRA"
      );
      buscarPrecio();
    });
  });

  // 3. Mostrar input personalizada cantidad si eligieron OTRA
  cantidadRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      cantidadPersonalizadaGroup.classList.toggle(
        "d-none",
        radio.nextElementSibling.textContent.trim().toUpperCase() !== "OTRA"
      );
      buscarPrecio();
    });
  });

  // 4. Escuchar cambios en campos que impactan precio
  [
    ...tipoPapelRadios,
    ...tipoColorRadios,
    ...terminacionRadios,
    ...numeradoRadios,
    ...tipoTroqueladoRadios
  ].forEach(radio => {
    radio.addEventListener("change", buscarPrecio);
  });

  // 5. Inputs personalizados también disparan búsqueda
  document.getElementById("medidaPersonalizada")?.addEventListener("input", buscarPrecio);
  document.getElementById("cantidad")?.addEventListener("input", buscarPrecio);

  // 6. Total editable si no hay precio de plantilla (precioBase == null)
  totalInput.addEventListener("input", () => {
    precioBase = null; // rompe vínculo con plantilla
    totalActual = parseInt(totalInput.value) || 0;
    actualizarResta();
  });

  // 7. Abonado input actualiza resta
  abonadoInput.addEventListener("input", actualizarResta);

  // 8. Adicional diseño suma/resta 5000
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

  // 9. Medio de pago: crédito suma/resta 10%
  medioPagoRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      const labelText = radio.nextElementSibling.textContent.trim().toUpperCase();
      const esCredito = labelText === "CRÉDITO";

      if (esCredito && !creditoAplicado) {
        totalActual = Math.round(totalActual * 1.10);
        creditoAplicado = true;
      } else if (!esCredito && creditoAplicado) {
        totalActual = Math.round(totalActual / 1.10);
        creditoAplicado = false;
      }
      actualizarTotal();
    });
  });

  // --- Funciones ---
  function buscarPrecio() {
    // Agarrar todos los valores seleccionados o personalizados
    const tipoPapelId = getCheckedValue(tipoPapelRadios);
    const tipoColorId = getCheckedValue(tipoColorRadios);
    const terminacionId = getCheckedValue(terminacionRadios);
    const medidaRadio = Array.from(medidaRadios).find(r => r.checked);
    const medidaId = medidaRadio ? medidaRadio.value : null;

    const numeradoId = getCheckedValue(numeradoRadios);
    const tipoTroqueladoId = getCheckedValue(tipoTroqueladoRadios);

    const cantidadRadio = Array.from(cantidadRadios).find(r => r.checked);
    const cantidadId = cantidadRadio ? cantidadRadio.value : null;

    // Si falta algún dato, no seguimos
    if (!tipoPapelId || !tipoColorId || !terminacionId || !medidaId || !numeradoId || !tipoTroqueladoId || !cantidadId) {
      return;
    }

    // Armar query params para la API
    const params = new URLSearchParams({
      tipoPapelEntradaId: tipoPapelId,
      tipoColorEntradaId: tipoColorId,
      terminacionEntradaId: terminacionId,
      medidaEntradaId: medidaId,
      numeradoEntradaId: numeradoId,
      tipoTroqueladoEntradaId: tipoTroqueladoId,
      cantidadEntradaId: cantidadId
    });

    fetch(`/api/plantilla-entrada/precio?${params.toString()}`)
      .then(res => res.ok ? res.text() : null)  // leo como texto porque es un int puro
      .then(data => {
        if (data !== null && data !== "") {
          precioBase = parseInt(data);
          totalActual = precioBase;
          adicionalAplicado = false;
          creditoAplicado = false;
          adicionalDisenioCheckbox.checked = false;
          medioPagoRadios.forEach(r => r.checked = false);
          totalInput.value = totalActual;
          totalInput.disabled = true;
          actualizarResta();
        } else {
          // No hay precio asociado, dejo total editable
          precioBase = null;
          totalInput.value = "";
          totalInput.disabled = false;
          totalActual = 0;
          actualizarResta();
        }
      });
  }

  function actualizarTotal() {
    totalInput.value = Math.round(totalActual);
    actualizarResta();
  }

  function actualizarResta() {
    const abonado = parseInt(abonadoInput.value) || 0;
    const total = parseInt(totalInput.value) || 0;
    restaInput.value = Math.max(total - abonado, 0);
  }

  function getCheckedValue(radios) {
    const checked = Array.from(radios).find(r => r.checked);
    return checked ? checked.value : null;
  }
});
