// auth.js

document.getElementById('loginForm').addEventListener('submit', async (event) => {
    event.preventDefault(); // Evita o recarregamento da página

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Faz uma requisição para o backend
    const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
    });

    if (response.ok) {
        const data = await response.json();
        // Redireciona com base na role do usuário
        if (data.role === 'ROLE_TRAINER') {
            window.location.href = 'dashboard_trainer.html';
        } else {
            window.location.href = 'dashboard_user.html';
        }
    } else {
        alert('Falha no login, verifique suas credenciais.');
    }
});

document.getElementById('registerForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
        alert('As senhas não coincidem');
        return;
    }

    const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
    });

    if (response.ok) {
        alert('Usuário registrado com sucesso! Faça login.');
        window.location.href = 'login.html';
    } else {
        alert('Erro ao registrar, tente novamente.');
    }
});
