document.addEventListener('DOMContentLoaded', function () {
    function escucharBotonAgregar() {
        document.getElementById('agregarItemConfigurable').addEventListener('click', function () {
            agregarItemConfigurable();
        });
    }

    let indexItemConfigurable = 0;

    function agregarItemConfigurable() {
        const itemsConfigurablesDiv = document.getElementById('itemsConfigurables');

        const template = document.createElement('div');
        template.classList.add('mb-3');

        // Usamos nombres compatibles con listas en Thymeleaf: itemConfigurables[0].nombre, itemConfigurables[0].opciones[0].valor
        const currentIndex = indexItemConfigurable++;

        template.innerHTML = `
            <form method="post" action="/crear-item-configurable" class="border p-3 rounded">
                <div class="row g-2">
                    <div class="col">
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
                    </div>
                    <div class="col">
                        <input type="text" name="tipo" class="form-control" placeholder="Tipo" required>
                    </div>
                    <div class="col-auto d-flex align-items-center">
                        <label class="form-check-label me-2">¿Obligatorio?</label>
                        <input type="checkbox" name="esObligatorio" class="form-check-input">
                    </div>
                </div>

                <div class="mt-3" id="opcionesContainer-${currentIndex}">
                    <!-- Opciones -->
                </div>

                <button type="button" class="btn btn-outline-secondary mt-2" onclick="agregarOpcion(${currentIndex})">+ Opción</button>
                <button type="submit" class="btn btn-success mt-2 ms-2">✔</button>
            </form>
        `;

        itemsConfigurablesDiv.appendChild(template);
    }

    window.agregarOpcion = function (index) {
        const contenedorOpciones = document.getElementById(`opcionesContainer-${index}`);

        const opcionHTML = `
            <div class="row g-2 mt-2">
                <div class="col">
                    <input type="text" name="opciones[0].valor" class="form-control" placeholder="Valor" required>
                </div>
                <div class="col">
                    <input type="number" step="0.01" name="opciones[0].precio" class="form-control" placeholder="Precio" required>
                </div>
            </div>
        `;

        contenedorOpciones.insertAdjacentHTML('beforeend', opcionHTML);
    }

    escucharBotonAgregar();
});
