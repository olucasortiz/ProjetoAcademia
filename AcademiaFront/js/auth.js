// auth.js

// Lógica de login
if (window.location.pathname.includes('login.html')) {
    document.querySelector('form').addEventListener('submit', function (e) {
        e.preventDefault();  // Impede o comportamento padrão do formulário

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password }),
        })
        .then(response => {
            if (response.ok) {
                window.location.href = 'dashboard.html';  // Redireciona para o dashboard
            } else {
                alert('Credenciais inválidas. Tente novamente.');
            }
        })
        .catch(error => console.error('Erro ao efetuar login:', error));
    });
}

// Lógica de registro
if (window.location.pathname.includes('register.html')) {
    document.querySelector('form').addEventListener('submit', function (e) {
        e.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (password !== confirmPassword) {
            alert('As senhas não coincidem!');
            return;
        }

        fetch('http://localhost:8080/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password }),
        })
        .then(response => {
            if (response.ok) {
                alert('Registro realizado com sucesso!');
                window.location.href = 'login.html';  // Redireciona para o login
            } else {
                alert('Erro ao registrar. Tente novamente.');
            }
        })
        .catch(error => console.error('Erro ao registrar:', error));
    });
}
