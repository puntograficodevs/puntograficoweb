document.addEventListener("DOMContentLoaded", () => {
    // Capturamos el ID del producto para asociarlo en los formularios siguientes
    const productoId = document.getElementById("productoId")?.value;
});

function agregarItemConfigurable() {
    const container = document.getElementById("itemsContainer");

    const itemIndex = Date.now(); // Para hacer único cada bloque
    const div = document.createElement("div");
    div.className = "row g-2 align-items-center border rounded p-3";
    div.innerHTML = `
        <form action="/item-configurable/crear" method="post" class="row g-2 align-items-center">
            <input type="hidden" name="productoId" value="${document.getElementById("productoId").value}" />
            <input type="hidden" name="username" value="${username}" />

            <div class="col-md-4">
                <input type="text" name="nombre" class="form-control" placeholder="Nombre del ítem" required />
            </div>
            <div class="col-md-4">
                <input type="text" name="tipo" class="form-control" placeholder="Tipo de ítem" required />
            </div>
            <div class="col-md-2">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="esObligatorio" id="obligatorio-${itemIndex}">
                    <label class="form-check-label" for="obligatorio-${itemIndex}">Obligatorio</label>
                </div>
            </div>
            <div class="col-md-2 d-grid">
                <button type="submit" class="btn btn-success">✔</button>
            </div>
        </form>

        <div class="mt-3">
            <button class="btn btn-sm btn-outline-primary" onclick="agregarOpcionItemConfigurable(this)">➕ Agregar opción</button>
            <div class="opciones-container mt-2 d-flex flex-column gap-2"></div>
        </div>
    `;
    container.appendChild(div);
}

function agregarOpcionItemConfigurable(button) {
    const opcionesContainer = button.parentElement.querySelector(".opciones-container");

    const itemConfigurableId = document.querySelector("input[name='itemConfigurableId']")?.value || "";

    const form = document.createElement("form");
    form.action = "/item-configurable-opcion/crear";
    form.method = "post";
    form.className = "row g-2 align-items-center";

    form.innerHTML = `
        <input type="hidden" name="itemConfigurableId" value="${itemConfigurableId}" />
        <input type="hidden" name="username" value="${username}" />

        <div class="col-md-4">
            <input type="text" name="valor" class="form-control" placeholder="Valor" required />
        </div>
        <div class="col-md-4">
            <input type="number" step="0.01" name="precio" class="form-control" placeholder="Precio" required />
        </div>
        <div class="col-md-2 d-grid">
            <button type="submit" class="btn btn-success">✔</button>
        </div>
    `;

    opcionesContainer.appendChild(form);
}

function agregarItemPersonalizable() {
    const container = document.getElementById("itemsContainer");

    const div = document.createElement("div");
    div.className = "row g-2 align-items-center border rounded p-3";
    div.innerHTML = `
        <form action="/item-personalizable/crear" method="post" class="row g-2 align-items-center">
            <input type="hidden" name="productoId" value="${document.getElementById("productoId").value}" />
            <input type="hidden" name="username" value="${username}" />

            <div class="col-md-4">
                <input type="text" name="nombre" class="form-control" placeholder="Nombre del ítem" required />
            </div>
            <div class="col-md-4">
                <input type="text" name="tipo" class="form-control" placeholder="Tipo" required />
            </div>
            <div class="col-md-2">
                <input type="number" step="0.01" name="precio" class="form-control" placeholder="Precio" required />
            </div>
            <div class="col-md-2 d-grid">
                <button type="submit" class="btn btn-success">✔</button>
            </div>
        </form>
    `;

    container.appendChild(div);
}

function finalizarProducto() {
    if (confirm("¿Seguro que querés finalizar y limpiar todo?")) {
        location.href = "/creacionProducto"; // Simple reload limpia los forms
    }
}

