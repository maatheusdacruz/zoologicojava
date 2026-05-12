<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="animalHealthOptions" value="${fn:split('HEALTHY,IN_TREATMENT,RECOVERING,SICK', ',')}" />

<section class="animal-page layout_padding2">
    <div class="animal-page__hero">
        <div>
            <h2 class="animal-page__title">Animal Management</h2>
            <p class="animal-page__subtitle">Create, update and monitor animals in one responsive and organized panel.</p>
        </div>
        <div class="animal-toolbar" role="group" aria-label="Animal actions">
            <button id="btnNewAnimal" type="button" class="btn animal-btn animal-btn--primary">New Animal</button>
            <button id="btnUpdateAnimal" type="button" class="btn animal-btn animal-btn--outline">Update Animal</button>
        </div>
    </div>

    <div id="dvNew" class="animal-panel" hidden>
        <div class="animal-panel__header">
            <h4 class="mb-0">Register New Animal</h4>
        </div>
        <div class="animal-panel__body">
            <form action="/" method="post" class="animal-form-grid" novalidate>
                <input type="hidden" name="ac" value="saveAnimal" />

                <div class="animal-form-field">
                    <label for="newAnimalName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="newAnimalName" name="cpName-animal" placeholder="Ex: Leo" required>
                </div>

                <div class="animal-form-field">
                    <label for="newAnimalScientificName" class="form-label">Scientific Name</label>
                    <input type="text" class="form-control" id="newAnimalScientificName" name="cpSciName-animal" placeholder="Ex: Panthera leo" required>
                </div>

                <div class="animal-form-field">
                    <label for="newAnimalSpecie" class="form-label">Specie</label>
                    <input type="text" class="form-control" id="newAnimalSpecie" name="cpSpecie-animal" placeholder="Ex: Lion" required>
                </div>

                <div class="animal-form-field">
                    <label for="newAnimalArrivalDate" class="form-label">Arrival Date</label>
                    <input type="date" class="form-control" id="newAnimalArrivalDate" name="cpArrivalDate-animal" required>
                </div>

                <div class="animal-form-field">
                    <label for="newAnimalPlace" class="form-label">Place</label>
                    <input type="text" class="form-control" id="newAnimalPlace" name="cpPlace-animal" placeholder="Ex: Savannah sector" required>
                </div>

                <div class="animal-form-field">
                    <label for="newAnimalHealth" class="form-label">Animal Health</label>
                    <select class="form-select" id="newAnimalHealth" name="cpHealth" required>
                        <option value="" disabled selected>Select animal health</option>
                        <c:forEach var="health" items="${animalHealthOptions}">
                            <option value="${health}">${health}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="animal-form-actions">
                    <button type="submit" class="btn animal-btn animal-btn--primary">Save Animal</button>
                </div>
            </form>
        </div>
    </div>

    <div id="dvUpdate" class="animal-panel" hidden>
        <div class="animal-panel__header animal-panel__header--accent">
            <h4 class="mb-0">Update Animal</h4>
        </div>
        <div class="animal-panel__body">
            <form action="/" method="post" class="animal-form-grid" novalidate>
                <input type="hidden" name="ac" value="updateAnimal" />

                <div class="animal-form-field animal-form-field--full">
                    <label for="updateAnimalSelect" class="form-label">Select an Animal</label>
                    <select id="updateAnimalSelect" name="cpId" class="form-select" required>
                        <option value="" selected disabled>Select an Animal</option>
                        <c:forEach var="a" items="${requestScope.animals}">
                            <option value="${a.id}">${a.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="animal-form-field">
                    <label for="updateAnimalName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="updateAnimalName" name="cpName-animal" placeholder="Update name">
                </div>

                <div class="animal-form-field">
                    <label for="updateAnimalScientificName" class="form-label">Scientific Name</label>
                    <input type="text" class="form-control" id="updateAnimalScientificName" name="cpSciName-animal" placeholder="Update scientific name">
                </div>

                <div class="animal-form-field">
                    <label for="updateAnimalSpecie" class="form-label">Specie</label>
                    <input type="text" class="form-control" id="updateAnimalSpecie" name="cpSpecie-animal" placeholder="Update specie">
                </div>

                <div class="animal-form-field">
                    <label for="updateAnimalArrivalDate" class="form-label">Arrival Date</label>
                    <input type="date" class="form-control" id="updateAnimalArrivalDate" name="cpArrivalDate-animal">
                </div>

                <div class="animal-form-field">
                    <label for="updateAnimalPlace" class="form-label">Place</label>
                    <input type="text" class="form-control" id="updateAnimalPlace" name="cpPlace-animal" placeholder="Update place">
                </div>

                <div class="animal-form-field">
                    <label for="updateAnimalHealth" class="form-label">Animal Health</label>
                    <select class="form-select" id="updateAnimalHealth" name="cpHealth">
                        <option value="" selected disabled>Select animal health</option>
                        <c:forEach var="health" items="${animalHealthOptions}">
                            <option value="${health}">${health}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="animal-form-actions">
                    <button type="submit" class="btn animal-btn animal-btn--primary">Save Changes</button>
                </div>
            </form>
        </div>
    </div>

    <div id="dvListagem" class="animal-panel animal-panel--list">
        <div class="animal-panel__header">
            <h4 class="mb-0">Animal List</h4>
        </div>
        <div class="animal-panel__body">
            <form action="/" method="get" class="animal-filter-form">
                <input type="hidden" name="ac" value="filterAnimals" />
                <div class="animal-form-field">
                    <label for="filterAnimalName" class="form-label">Search by Name</label>
                    <input type="text" class="form-control" id="filterAnimalName" name="cpName-get" placeholder="Type a name">
                </div>
                <button type="submit" class="btn animal-btn animal-btn--secondary">Search</button>
            </form>

            <div class="animal-table-wrap">
                <table class="table align-middle animal-table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Scientific Name</th>
                        <th>Specie</th>
                        <th>Arrival Date</th>
                        <th>Place</th>
                        <th>Animal Health</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty requestScope.animals}">
                        <tr>
                            <td colspan="7" class="text-center py-4">No animals found.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="a" items="${requestScope.animals}">
                        <tr>
                            <td data-label="Name">${a.name}</td>
                            <td data-label="Scientific Name">${a.scientificName}</td>
                            <td data-label="Specie">${a.specie}</td>
                            <td data-label="Arrival Date">${a.arrivalDate}</td>
                            <td data-label="Place">${a.place}</td>
                            <td data-label="Animal Health"><span class="animal-badge">${a.animalhealth}</span></td>
                            <td data-label="Actions">
                                <div class="animal-table-actions">
                                    <button type="button" class="btn btn-sm animal-btn animal-btn--outline btnEdit" data-animal-id="${a.id}">Edit</button>
                                    <form method="post" action="/" class="d-inline">
                                        <input type="hidden" name="ac" value="deleteAnimal" />
                                        <input type="hidden" name="id-animal" value="${a.id}" />
                                        <button type="submit" class="btn btn-sm animal-btn animal-btn--danger">Delete</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<script>
    const btnNew = document.querySelector("#btnNewAnimal");
    const btnUpdate = document.querySelector("#btnUpdateAnimal");
    const dvNew = document.querySelector("#dvNew");
    const dvUpdate = document.querySelector("#dvUpdate");
    const dvList = document.querySelector("#dvListagem");
    const selectAnimal = document.querySelector("#updateAnimalSelect");

    let currentView = "list";

    function updateToolbarLabels() {
        const isNewActive = currentView === "new";
        const isUpdateActive = currentView === "update";

        btnNew.textContent = isNewActive ? "Back to List" : "New Animal";
        btnUpdate.textContent = isUpdateActive ? "Back to List" : "Update Animal";

        btnNew.classList.toggle("is-active", isNewActive);
        btnUpdate.classList.toggle("is-active", isUpdateActive);
    }

    function showView(view) {
        currentView = view;

        dvList.hidden = view !== "list";
        dvNew.hidden = view !== "new";
        dvUpdate.hidden = view !== "update";

        updateToolbarLabels();
    }

    btnNew.addEventListener("click", () => {
        showView(currentView === "new" ? "list" : "new");
    });

    btnUpdate.addEventListener("click", () => {
        showView(currentView === "update" ? "list" : "update");
    });

    document.querySelectorAll(".btnEdit").forEach((btn) => {
        btn.addEventListener("click", function() {
            const animalId = this.getAttribute("data-animal-id");
            if (selectAnimal) {
                selectAnimal.value = animalId;
            }
            showView("update");
        });
    });
</script>
