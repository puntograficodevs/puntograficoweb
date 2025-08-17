document.addEventListener('DOMContentLoaded', () => {
    const botonesVer = document.querySelectorAll('.ver-btn');
    const modalElement = document.getElementById('ordenModal');
    const cuerpoModal = document.getElementById("modalBody");

    // Inicializar modal de Bootstrap una sola vez
    const modal = new bootstrap.Modal(modalElement);

    botonesVer.forEach(boton => {
        boton.addEventListener('click', async () => {
            const idOrden = boton.dataset.idorden;
            const tipoProducto = boton.dataset.tipoproducto;

            // Mostrar loader
            cuerpoModal.innerHTML = '<p class="text-center">Cargando los datos de la orden...</p>';

            try {
                if (tipoProducto === "agenda") {
                    const orden = await fetch("/api/orden/ordenAgenda/" + idOrden)
                        .then(res => res.json());

                    cuerpoModal.innerHTML = renderOrdenAgenda(orden);
                    modal.show();
                }
                // Aquí puedes agregar más tipos de producto si los tienes
            } catch (error) {
                cuerpoModal.innerHTML = '<p class="text-danger text-center">Error al cargar la orden.</p>';
                console.error(error);
            }
        });
    });

    // Limpiar modal al cerrarlo (no hace falta remover backdrop manualmente)
    modalElement.addEventListener('hidden.bs.modal', () => {
        cuerpoModal.innerHTML = '';
    });

    function renderOrdenAgenda(orden) {
        return `<div class="row mt-4">
            <div class="col-6">
                <div class="card mb-3 border contenedor-datos-orden-impresion">
                    <h6>Datos del cliente</h6>
                    <hr class="divider">
                    <p><strong>Cliente: </strong>${orden.ordenTrabajo.nombreCliente}</p>
                    <p><strong>Teléfono: </strong>${orden.ordenTrabajo.telefonoCliente}</p>
                    <p><strong>Cuenta corriente: </strong>${orden.ordenTrabajo.esCuentaCorriente ? 'Sí' : 'No'}</p>
                </div>
                <div class="card mb-3 border contenedor-datos-orden-impresion">
                    <h6>Datos del pago</h6>
                    <hr class="divider">
                    <p><strong>Pedido: </strong>${orden.ordenTrabajo.fechaPedido}</p>
                    <p><strong>Total: </strong>$${orden.ordenTrabajo.total}</p>
                    <p><strong>Abonado: </strong>$${orden.ordenTrabajo.abonado}</p>
                    <p class="pago-restante"><strong>Restante: </strong>$${orden.ordenTrabajo.resta}</p>
                    <p><strong>Medio de pago: </strong>${orden.ordenTrabajo.medioPago.medioDePago}</p>
                    <p><strong>Necesita factura: </strong>${orden.ordenTrabajo.necesitaFactura ? 'Sí' : 'No'}</p>
                    <p><strong>Vendedor/a: </strong>${orden.ordenTrabajo.empleado.nombre}</p>
                </div>
            </div>
            <div class="col-6">
                <div class="card mb-3 border contenedor-datos-orden-impresion">
                    <h6>Datos del producto</h6>
                    <hr class="divider">
                    <p><strong>Medida: </strong>${orden.agenda.medida}</p>
                    <p><strong>Tipo de tapa: </strong>${
                        orden.agenda.tipoTapaAgenda.tipo === 'OTRA'
                        ? orden.agenda.tipoTapaPersonalizada
                        : orden.agenda.tipoTapaAgenda.tipo
                    }</p>
                    <p><strong>Color: </strong>${orden.agenda.tipoColorAgenda.tipo}</p>
                    <p><strong>Cantidad de hojas: </strong>${orden.agenda.cantidadHojas}</p>
                    <hr class="divider">
                    <p><strong>Adicional diseño: </strong>${orden.agenda.conAdicionalDisenio ? 'Sí' : 'No'}</p>
                    <p><strong>Enlace al archivo: </strong>${
                        orden.agenda.enlaceArchivo ? `<a href="${orden.agenda.enlaceArchivo}" target="_blank">${orden.agenda.enlaceArchivo}</a>` : '-'
                    }</p>
                    <p><strong>Información adicional: </strong>${orden.agenda.informacionAdicional || '-'}</p>
                    <hr class="divider">
                    <p><strong>Fecha de entrega: </strong>${orden.ordenTrabajo.fechaEntrega}</p>
                    ${orden.ordenTrabajo.horaEntrega ? `<p><strong>Hora de entrega: </strong>${orden.ordenTrabajo.horaEntrega}</p>` : ''}
                    ${orden.ordenTrabajo.fechaMuestra ? `<p><strong>Fecha de muestra: </strong>${orden.ordenTrabajo.fechaMuestra}</p>` : ''}
                    <p><strong>Cantidad de productos: </strong>${orden.agenda.cantidad}</p>
                </div>
            </div>
        </div>`;
    }
});
