document.getElementById('loginForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    console.log("Tentando login com: ", { email, password });

    if (!email || !password) {
        alert('Por favor, preencha ambos os campos de email e senha.');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            const data = await response.json();
            console.log('Resposta do servidor:', data);

            if (data.user.role === 'ROLE_TRAINER') {
                window.location.href = '/dashboard_trainer.html';
            } else {
                window.location.href = '/pages/dashboard_user.html';
            }
        } else {
            const errorData = await response.json();
            console.error('Erro na resposta:', errorData);
            alert(`Falha no login: ${errorData.message || 'Erro desconhecido'}`);
        }
    } catch (error) {
        console.error('Erro ao realizar login:', error);
        alert('Erro ao tentar fazer login.');
    }
});
