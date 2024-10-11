document.getElementById('loginForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            const data = await response.json();
            if (data.role === 'ROLE_TRAINER') {
                window.location.href = '/dashboard_trainer.html';
            } else {
                window.location.href = '/dashboard_user.html';
            }
        } else {
            alert('Falha no login, verifique suas credenciais.');
        }
    } catch (error) {
        console.error('Erro ao realizar login:', error);
        alert('Erro ao tentar fazer login.');
    }
});
