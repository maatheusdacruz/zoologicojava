<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-5 d-flex justify-content-center">
    <div class="card shadow p-4" style="max-width: 450px; border-radius: 15px; width: 100%;">
        <form action="/" method="post">
            <h4 class="text-center mb-4" style="color: #B22222; font-family: 'Cinzel', serif;">Cadastro</h4>

            <input type="hidden" name="ac" value="valsignup" />

            <div class="mb-3">
                <label for="inputName" class="form-label">Nome</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputName"
                        name="cpName"
                        placeholder="Digite o seu nome..."
                        required
                />
            </div>

            <div class="mb-3">
                <label for="inputEmail" class="form-label">E-mail</label>
                <input
                        type="email"
                        class="form-control"
                        id="inputEmail"
                        name="cpLogin"
                        placeholder="Digite o seu email..."
                        required
                />
            </div>

            <div class="mb-4">
                <label for="inputPassword" class="form-label">Senha</label>
                <input
                        type="password"
                        class="form-control"
                        id="inputPassword"
                        name="cpPassword"
                        placeholder="Digite a sua senha..."
                        required
                />
            </div>

            <c:if test="${sessionScope.Loggedin != null
                  && sessionScope.Loggedin.equals('1')
                  && sessionScope.userType == 'ADMIN'}">
                <div class="mb-4">
                    <label for="selectUserType" class="form-label">Tipo de Usuario</label>
                    <select class="form-select" id="selectUserType" name="radio-user" required>
                        <option value="" disabled selected>Selecione um tipo</option>
                        <option value="ADMIN">ADMIN</option>
                        <option value="WORKER">WORKER</option>
                        <option value="VISITOR">VISITOR</option>
                    </select>
                </div>
            </c:if>

            <div class="d-grid">
                <button type="submit" class="btn" style="background-color: #FFD700; color: #000; font-weight: 600;">Cadastrar</button>
            </div>
        </form>
    </div>
</div>

<c:if test="${sessionScope.Loggedin != null
        && sessionScope.Loggedin.equals('1')
        && sessionScope.userType == 'ADMIN'}">
    <div class="text-center mt-3">
        <a class="nav-link" href="?ac=editView" style="color: #B22222; font-weight: 600;">Editar Trabalhador</a>
    </div>
</c:if>
