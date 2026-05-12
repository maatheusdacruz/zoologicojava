<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="showTask" style="max-width: 600px; margin: 40px auto; padding: 20px; background: #fff; border-radius: 12px; box-shadow: 0 0 15px rgba(0,0,0,0.1); font-family: Arial, sans-serif;">
    <form id="editTaskForm" method="post" action="/">
        <input type="hidden" name="ac" value="updateTask" />

        <div id="taskView">
            <label for="taskTitle">Worker:</label>
            <input type="text" id="taskTitle" class="form-control" value="${reportOnly.title}" disabled
                   style="width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 8px; border: 1px solid #ccc;" />

            <label for="taskDesc">Description:</label>
            <textarea id="taskDesc" class="form-control" disabled
                      style="width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 8px; border: 1px solid #ccc;">${reportOnly.description}</textarea>

            <label for="taskWorker">Trabalhador:</label>
            <input type="text" id="taskWorker" class="form-control" value="${reportOnly.worker.name}" disabled
                   style="width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 8px; border: 1px solid #ccc;" />

            <button type="button" onclick="enableEdit()"
                    style="margin-top: 10px; padding: 8px 16px; border: none; background-color: #007BFF; color: white; border-radius: 8px; cursor: pointer;">
                Editar
            </button>
        </div>



        <div id="taskEdit" style="display: none;">
            <label for="title">Worker:</label>
            <input type="text" name="cpTitle" id="editTitle" class="form-control" value="${reportOnly.title}" required
                   style="width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 8px; border: 1px solid #ccc;" />

            <label for="desc">Description:</label>
            <textarea name="cpDescription" id="editDesc" class="form-control" required
                      style="width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 8px; border: 1px solid #ccc;">${reportOnly.description}</textarea>

            <input type="hidden" name="cpWorkerId" value="${reportOnly.worker.id}" />
            <input type="hidden" name="cpTaskId" value="${reportOnly.id}" />

            <div style="display: flex; gap: 10px;">
                <button type="submit" style="padding: 8px 16px; background-color: #28a745; color: white; border: none; border-radius: 8px;">Salvar</button>
                <button type="button" onclick="cancelEdit()" style="padding: 8px 16px; background-color: #dc3545; color: white; border: none; border-radius: 8px;">Cancelar</button>
            </div>
        </div>
    </form>
</div>

<script>
    function enableEdit() {
        document.getElementById("taskView").style.display = "none";
        document.getElementById("taskEdit").style.display = "block";
    }

    function cancelEdit() {
        document.getElementById("taskEdit").style.display = "none";
        document.getElementById("taskView").style.display = "block";
    }
</script>
