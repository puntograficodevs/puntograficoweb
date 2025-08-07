document.addEventListener("DOMContentLoaded", () => {
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");

  const medidaRadios = document.querySelectorAll(".medida-radio");
  const medidaPersonalizadaGroup = document.getElementById("medidaPersonalizadaGroup");

  const cantidadRadios = document.querySelectorAll(".cantidad-radio");
  const cantidadManualGroup = document.getElementById("cantidadManualGroup");
  const cantidadManualInput = document.getElementById("cantidad");

  const tipoTroqueladoRadios = document.querySelectorAll("input[name='tipoTroqueladoCierraBolsas.id']");
  const mediosDePagoRadios = document.querySelectorAll("input[name='medioPago.id']");
  const adicionalDisenioCheckbox = document.getElementById("cierra-bolsas-con-adicional-disenio");

  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");

  let selectedTipoId = null;
  let selectedMedidaId = null;
  let selectedCantidadId = null;

  let basePrecio = null;

  const limpiarTotal = () => {
    totalInput.value = "";
    restaInput.value = "";
    basePrecio = null;
  };

  const actualizarResta = () => {
    const total = parseInt(totalInput.value) || 0;
    const abonado = parseInt(abonadoInput.value) || 0;
    restaInput.value = total - abonado;
  };

  const aplicarExtras = () => {
    // Intentar obtener basePrecio si no lo teníamos, desde el input de total
    if (basePrecio === null) {
      const totalActual = parseInt(totalInput.value);
      if (!isNaN(totalActual)) {
        basePrecio = totalActual;
      } else {
        return; // No hay base sobre la cual operar
      }
    }

    let total = basePrecio;

    if (adicionalDisenioCheckbox.checked) {
      total += 5000;
    }

    const medioCredito = Array.from(mediosDePagoRadios).find(radio =>
      radio.checked && radio.nextElementSibling.textContent.toUpperCase().includes("CRÉDITO")
    );
    if (medioCredito) {
      total = Math.round(total * 1.10);
    }

    totalInput.value = total;
    actualizarResta();
  };

  const calcularTotal = () => {
    if (!selectedTipoId || !selectedMedidaId || !selectedCantidadId) {
      limpiarTotal();
      return;
    }

    fetch(`/api/plantilla-cierra-bolsas/precio?tipoTroqueladoCierraBolsasId=${selectedTipoId}&medidaCierraBolsasId=${selectedMedidaId}&cantidadCierraBolsasId=${selectedCantidadId}`)
      .then(res => {
        if (res.status === 204) return null;
        return res.json();
      })
      .then(precioBase => {
        if (precioBase === null) {
          limpiarTotal();
          return;
        }

        basePrecio = precioBase;
        aplicarExtras();
      })
      .catch(err => {
        console.error("Error obteniendo precio:", err);
        limpiarTotal();
      });
  };

  // Mostrar/ocultar Fecha Muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // Cambios en medida
  medidaRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      const label = radio.nextElementSibling.textContent.trim().toUpperCase();
      if (label === "OTRA") {
        medidaPersonalizadaGroup.classList.remove("d-none");
        selectedMedidaId = null;
      } else {
        medidaPersonalizadaGroup.classList.add("d-none");
        selectedMedidaId = radio.value;
      }
      limpiarTotal();
      calcularTotal();
    });
  });

  // Cambios en cantidad
  cantidadRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      const label = radio.nextElementSibling.textContent.trim().toUpperCase();
      if (label === "OTRA") {
        cantidadManualGroup.classList.remove("d-none");
        selectedCantidadId = null;
      } else {
        cantidadManualGroup.classList.add("d-none");
        selectedCantidadId = radio.value;
      }
      limpiarTotal();
      calcularTotal();
    });
  });

  // Cambios en tipo troquelado
  tipoTroqueladoRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      selectedTipoId = radio.value;
      limpiarTotal();
      calcularTotal();
    });
  });

  // Cambios en cantidad personalizada (invalida fetch)
  cantidadManualInput.addEventListener("input", () => {
    selectedCantidadId = "9999";
    limpiarTotal();
  });

  // Cambios en medios de pago (solo suma 10%)
  mediosDePagoRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      aplicarExtras();
    });
  });

  // Cambios en adicional diseño (solo suma 5000)
  adicionalDisenioCheckbox.addEventListener("change", () => {
    aplicarExtras();
  });

  // Cambios en abonado
  abonadoInput.addEventListener("input", () => {
    actualizarResta();
  });
});
