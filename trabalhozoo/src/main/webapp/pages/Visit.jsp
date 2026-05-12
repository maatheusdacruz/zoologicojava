<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="container">
    <h1 class="mb-4">Visits</h1>




    <section id="dvTable" class="view-section">
        <div class="table-responsive shadow-sm rounded">
            <table class="table table-hover table-bordered align-middle bg-white">
                <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Visitor</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${sessionScope.userType == 'ADMIN'}">
                        <c:forEach var="i" items="${visits}">
                            <tr>
                                <td>${i.id}</td>
                                <td>${i.date}</td>
                                <td>${i.visitor.name}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${sessionScope.userType == 'Visitor'}">
                        <c:forEach var="i" items="${VisitsVisitor}">
                            <tr>
                                <td>${i.id}</td>
                                <td>${i.date}</td>
                                <td>${i.visitor.name}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="3" class="text-center text-muted">Nenhuma visita registrada.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </section>





    <section id="dvVisits" class="form-section" style="display: none;">
        <div class="card p-4 shadow-sm">
            <h2 class="mb-3">Registrar Visita</h2>
            <form action="/" method="POST">
                <input type="hidden" name="ac" value="visits" />

                <c:if test="${sessionScope.userType == 'ADMIN'}">
                    <div class="mb-3">
                        <label for="visitorSelect" class="form-label">Visitor:</label>
                        <select id="visitorSelect" name="cpId-visitor" required class="form-select">
                            <option value="" disabled selected>Select a Visitor</option>
                            <c:forEach var="a" items="${requestScope.visitors}">
                                <option value="${a.id}">${a.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="inputVisitDate" class="form-label">Visit Date:</label>
                        <input type="date" class="form-control" id="inputVisitDate" name="cpVisit-Date" required>
                    </div>
                </c:if>

                <button type="submit" class="btn btn-success w-100">Registrar</button>
            </form>
        </div>
    </section>





    <div class="text-end mt-3">
        <button type="button" id="btnTasks" class="btn btn-outline-secondary">Registrar Visita</button>
    </div>
</main>




<script>
    const dvTable = document.getElementById('dvTable');
    const dvVisits = document.getElementById('dvVisits');
    const btnTasks = document.getElementById('btnTasks');

    btnTasks.addEventListener('click', () => {
        const isShowingTasks = dvVisits.style.display === 'block';
        dvTable.style.display = isShowingTasks ? 'block' : 'none';
        dvVisits.style.display = isShowingTasks ? 'none' : 'block';
        btnTasks.textContent = isShowingTasks ? 'Registrar Visita' : 'Voltar';
    });
</script>
