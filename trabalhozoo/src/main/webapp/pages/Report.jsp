<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="dvTable" style="background: #fff; padding: 32px 40px; border-radius: 12px; box-shadow: 0 4px 16px rgba(0,0,0,0.08); max-width: 920px; margin: 50px auto; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #444;">

    <form method="get" style="margin-bottom: 32px; display: flex; align-items: center; gap: 14px;">
        <label for="data" style="font-weight: 700; color: #2c3e50; font-size: 1rem;">Data:</label>
        <input name="ac" type="hidden" value="report"/>
        <input type="date" name="data" value="${queryDate}"
               style="padding: 10px 14px; border: 1.5px solid #bbb; border-radius: 10px; font-size: 1rem; color: #333; transition: border-color 0.3s ease;"
               onfocus="this.style.borderColor='#000000'"
               onblur="this.style.borderColor='#bbb'"/>
        <button type="submit"
                style="padding: 12px 28px; background-color: #FFD700; color: white; font-weight: 600; border: none; border-radius: 10px; cursor: pointer; box-shadow: 0 3px 6px rgba(216,189,51,0.5); transition: background-color 0.3s ease, box-shadow 0.3s ease;">
            Filter
        </button>
    </form>

    <table style="width: 100%; border-collapse: separate; border-spacing: 0 12px; font-size: 0.95rem;">
        <thead>
        <tr style="background-color: #FFD700; color: #fff; font-weight: 700; box-shadow: 0 2px 8px rgba(216,189,51,0.5);">
            <th style="padding: 14px 20px;">Title</th>
            <th style="padding: 14px 20px;">Worker</th>
            <th style="padding: 14px 20px; border-radius: 0 12px 12px 0;">Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${sessionScope.userType == 'ADMIN'}">
            <c:forEach var="i" items="${reports}">
                <tr style="background: #fafafa; box-shadow: 0 1px 4px rgba(0,0,0,0.05);">
                    <td style="padding: 14px 20px;">${i.title}</td>
                    <td style="padding: 14px 20px;">${i.worker.name}</td>
                    <td style="padding: 14px 20px;">
                        <form>
                            <input type="hidden" name="ac" value="taskOnly"/>
                            <input type="hidden" name="idTask" value="${i.id}"/>
                            <button type="submit"
                                    style="padding: 10px 22px; background-color: #FFD700; color: white; border: none; border-radius: 10px; font-weight: 600; cursor: pointer; transition: background-color 0.3s ease, box-shadow 0.3s ease;">
                                Ver Completo
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.userType == 'WORKER'}">
            <c:forEach var="i" items="${reportsWorker}">
                <tr style="background: #fafafa; box-shadow: 0 1px 4px rgba(0,0,0,0.05);">
                    <td style="padding: 14px 20px; font-weight: 600; color: #2c3e50;">${i.id}</td>
                    <td style="padding: 14px 20px;">${i.title}</td>
                    <td style="padding: 14px 20px;">${i.worker.name}</td>
                    <td style="padding: 14px 20px;">
                        <form>
                            <input type="hidden" name="ac" value="taskOnly"/>
                            <input type="hidden" name="idTask" value="${i.id}"/>
                            <button type="submit"
                                    style="padding: 10px 22px; background-color: #FFD700; color: white; border: none; border-radius: 10px; font-weight: 600; cursor: pointer; transition: background-color 0.3s ease, box-shadow 0.3s ease;">
                                Ver Completo
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

<div id="dvtasks" style="display: none; background: #fff; padding: 32px 40px; border-radius: 12px; box-shadow: 0 4px 16px rgba(0,0,0,0.08); max-width: 920px; margin: 50px auto; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #444;">
    <h2 style="color: #2c3e50; margin-bottom: 28px; font-weight: 700;">Tasks</h2>
    <form action="/" method="POST" style="display: flex; flex-direction: column; gap: 20px;">
        <input type="hidden"  name="ac" value="tasks" />
        <c:if test="${sessionScope.userType == 'ADMIN'}">
            <select name="cpId-Worker" style="padding: 12px 14px; border-radius: 10px; border: 1.5px solid #bbb; font-size: 1rem; color: #333;">
                <option>Select an worker</option>
                <c:forEach var="a" items="${requestScope.workers}">
                    <option value="${a.id}">${a.name}</option>
                </c:forEach>
            </select>
        </c:if>

        <label for="Title" style="font-weight: 700; color: #2c3e50; font-size: 1rem;">Title:</label>
        <input id="Title" name="cpTitle" class="form-control" placeholder=""
               style="padding: 12px 14px; border-radius: 10px; border: 1.5px solid #bbb; font-size: 1rem; color: #333;"/>

        <label for="Description" style="font-weight: 700; color: #2c3e50; font-size: 1rem;">Description:</label>
        <input id="Description" name="cpDescription" class="form-control" placeholder=""
               style="padding: 12px 14px; border-radius: 10px; border: 1.5px solid #bbb; font-size: 1rem; color: #333;"/>

        <button type="submit"
                style="padding: 14px 30px; background-color: #FFD700; color: white; font-weight: 700; border: none; border-radius: 10px; cursor: pointer; box-shadow: 0 3px 6px rgba(216,189,51,0.5); transition: background-color 0.3s ease, box-shadow 0.3s ease;">
            Save
        </button>
    </form>
</div>

<button type="button" id="btnTasks"
        style="display: block; margin: 40px auto 60px auto; padding: 14px 44px; background-color: #FFD700; color: white; font-weight: 700; border: none; border-radius: 10px; cursor: pointer; box-shadow: 0 4px 12px rgba(216,189,51,0.5); transition: background-color 0.3s ease, box-shadow 0.3s ease;">
    Tasks
</button>

<script>
    let dvTable = document.getElementById('dvTable');
    let dvtasks = document.getElementById('dvtasks');
    let btnTasks = document.getElementById('btnTasks');

    btnTasks.addEventListener('click', () => {
        if (btnTasks.innerHTML == 'Tasks') {
            btnTasks.innerHTML = 'Back';
            dvTable.style.display = 'none';
            dvtasks.style.display = 'block';
        } else {
            btnTasks.innerHTML = 'Tasks';
            dvtasks.style.display = 'none';
            dvTable.style.display = 'block';
        }
    });
</script>
