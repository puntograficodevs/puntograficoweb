document.addEventListener('DOMContentLoaded', () => {
    const botonesVer = document.querySelectorAll('.ver-btn');

    botonesVer.forEach(boton => {

        boton.addEventListener('click', async () => { // async agregado
            const idOrden = boton.dataset.idorden;
            const tipoProducto = boton.dataset.tipoproducto;
            const cuerpoModal = document.getElementById("modalBody"); // string

            switch(tipoProducto) {
                case "agenda":
                    const orden = await fetch("/api/orden/ordenAgenda/" + idOrden)
                        .then(res => res.json());

                    cuerpoModal.innerHTML = `
                        <p>${orden.ordenTrabajo.nombreCliente}</p>
                    `;
                    break;
            }
        });

    });
});
