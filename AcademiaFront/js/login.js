// Aguarda o evento de envio do formulário de login
document.getElementById('loginForm').addEventListener('submit', async (event) => {
    event.preventDefault(); // Previne o comportamento padrão do formulário de recarregar a página

    // Obtém os valores dos campos de email e senha
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    console.log("Tentando login com: ", { email, password });

    // Verifica se ambos os campos estão preenchidos
    if (!email || !password) {
        alert('Por favor, preencha ambos os campos de email e senha.');
        return; // Retorna para impedir que a requisição seja enviada
    }

    try {
        // Faz a requisição POST para a API de login
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({ email, password }) // Envia o email e senha no corpo da requisição
        });

        // Verifica se o login foi bem-sucedido (código 200)
        if (response.ok) {
            const data = await response.json(); // Extrai o JSON da resposta
            console.log('Resposta do servidor:', data);

            // Verifica o papel (role) do usuário e redireciona para a página adequada
            if (data.user.role === 'ROLE_TRAINER') {
                window.location.href = '/pages/dashboard_trainer.html'; // Redireciona para a página do treinador
            } else {
                window.location.href = '/pages/dashboard_user.html'; // Redireciona para a página do usuário comum
            }
        } else {
            // Se o login falhar, exibe uma mensagem de erro
            const errorData = await response.json();
            console.error('Erro na resposta:', errorData);
            alert(`Falha no login: ${errorData.message || 'Erro desconhecido'}`);
        }
    } catch (error) {
        // Captura erros relacionados à rede ou a execução do código
        console.error('Erro ao realizar login:', error);
        alert('Erro ao tentar fazer login. Por favor, tente novamente mais tarde.');
    }
});
    