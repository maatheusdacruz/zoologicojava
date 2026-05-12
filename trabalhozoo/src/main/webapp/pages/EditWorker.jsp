<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="edit-worker-container" style="max-width: 500px; margin: 40px auto; padding: 20px; background: #f9f9f9; border-radius: 12px; box-shadow: 0 0 15px rgba(0,0,0,0.1); font-family: Arial, sans-serif;">
    <form method="post" action="/">
        <input type="hidden" name="ac" value="editWorker" />

        <label for="selectWorker" style="font-weight: 600; color: #333;">Select a worker:</label>
        <select id="selectWorker" name="cpIdWorker" class="form-control" required style="width: 100%; padding: 8px; border-radius: 8px; border: 1px solid #ccc; margin-bottom: 20px;">
            <option value="" disabled selected>Select a worker</option>
            <c:forEach var="w" items="${requestScope.workers}">
                <option value="${w.id}">${w.name}</option>
            </c:forEach>
        </select>

        <label for="NameWorker" style="font-weight: 600; color: #333;">Name:</label>
        <input id="NameWorker" type="text" name="cpNameWorker" class="form-control" placeholder="" style="width: 100%; padding: 8px; border-radius: 8px; border: 1px solid #ccc; margin-bottom: 20px;" />

        <label for="EmailWorker" style="font-weight: 600; color: #333;">Email:</label>
        <input id="EmailWorker" type="email" name="cpEmailWorker" class="form-control" placeholder="" style="width: 100%; padding: 8px; border-radius: 8px; border: 1px solid #ccc; margin-bottom: 20px;" />

        <label for="PasswordWorker" style="font-weight: 600; color: #333;">Password:</label>
        <input id="PasswordWorker" type="password" name="cpPasswordWorker" class="form-control" placeholder="" style="width: 100%; padding: 8px; border-radius: 8px; border: 1px solid #ccc; margin-bottom: 30px;" />

        <div class="form-buttons" style="display: flex; justify-content: space-between; gap: 15px;">
            <button type="submit" name="btnEdit" value="update" class="btn btn-primary" style="flex: 1; padding: 12px; background-color: #4CAF50; color: white; border: none; border-radius: 8px; cursor: pointer;">Atualizar</button>
            <button type="submit" name="btnEdit" value="delete" class="btn btn-danger" style="flex: 1; padding: 12px; background-color: #f44336; color: white; border: none; border-radius: 8px; cursor: pointer;">Excluir</button>
        </div>
    </form>
</div>
