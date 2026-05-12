<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container my-4" style="max-width: 900px; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
  <form method="get" class="mb-3 d-flex align-items-end gap-2">
    <input name="ac" type="hidden" value="food" />
    <div class="form-group">
      <label for="query-date" class="form-label">Date:</label>
      <input type="date" id="query-date" name="query-date" value="${queryDate}" class="form-control" />
    </div>
    <button type="submit" class="btn btn-warning">Filter</button>
  </form>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Animal</th>
      <th>Fed?</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="i" items="${FoodReport}">
      <tr>
        <td>${i.animal.name}</td>
        <td>
          <c:choose>
            <c:when test="${i.wasFed}">Sim</c:when>
            <c:otherwise>Nao</c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="container my-4" style="max-width: 900px; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); display: none;" id="dvfeed">
  <h2>Feed Animal</h2>
  <form action="/" method="POST" class="mb-3">
    <input type="hidden" name="ac" value="feed" />

    <div class="mb-3">
      <label for="animalSelect" class="form-label">Select an animal</label>
      <select id="animalSelect" name="cpId" required class="form-select">
        <option value="" disabled selected>Select an animal</option>
        <c:forEach var="a" items="${requestScope.animals}">
          <option value="${a.id}">${a.name}</option>
        </c:forEach>
      </select>
      <label for="Food" class="form-label">Type Food</label>
      <input type="text" id="Food" name="cpTypeFood"/>
    </div>

    <div class="mb-3">
      <label for="workerSelect" class="form-label">Select a worker</label>
      <select id="workerSelect" name="cpId-Worker" required class="form-select">
        <option value="" disabled selected>Select a worker</option>
        <c:forEach var="a" items="${requestScope.workers}">
          <option value="${a.id}">${a.name}</option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-warning">Save</button>
  </form>
</div>

<div class="container my-4" style="max-width: 900px;">
  <button type="button" id="btnFeed" class="btn btn-warning">Feed</button>
</div>

<div id="dvListagem" class="row layout_padding2-bottom">
  <div class="col-12">
    <div class="card shadow rounded-4">
      <div class="card-body">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
          <tr>
            <th>Animal</th>
            <th>Date and Time</th>
            <th>Type of Food</th>
            <th>Worker</th>

          </tr>
          </thead>
          <tbody>
          <c:forEach var="a" items="${requestScope.foodPerDay}">
            <tr>
              <td>${a.animal.name}</td>
              <td>${a.dateTime}</td>
              <td>${a.typeFood}</td>
              <td>${a.worker.name}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>


<script>
  const dvTable = document.querySelector('div.container:nth-of-type(1)');
  const dvFeed = document.getElementById('dvfeed');
  const btnFeed = document.getElementById('btnFeed');

  btnFeed.addEventListener('click', () => {
    if (btnFeed.textContent === 'Feed') {
      btnFeed.textContent = 'Back';
      dvTable.style.display = 'none';
      dvFeed.style.display = 'block';
    } else {
      btnFeed.textContent = 'Feed';
      dvFeed.style.display = 'none';
      dvTable.style.display = 'block';
    }
  });
</script>
