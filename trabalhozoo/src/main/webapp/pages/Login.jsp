<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-5 d-flex justify-content-center">
    <div class="card shadow p-4" style="max-width: 400px; border-radius: 15px; width: 100%;">
        <form action="/" method="POST">
            <h4 class="text-center mb-4" style="color: #B22222; font-family: 'Cinzel', serif;">Login</h4>

            <input type="hidden" name="ac" value="vallog" />

            <div class="mb-3">
                <label for="inputEmail" class="form-label">E-mail</label>
                <input
                        type="email"
                        class="form-control"
                        id="inputEmail"
                        name="cpLogin"
                        placeholder="Digite o seu email..."
                        required
                        autocomplete="username"
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
                        autocomplete="current-password"
                />
            </div>

            <div class="d-grid">
                <button type="submit" class="btn" style="background-color: #FFD700; color: #000; font-weight: 600;">Entrar</button>
            </div>
        </form>
    </div>
</div>
