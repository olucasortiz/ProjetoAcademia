// user.js
class UserService {
    static async registerUser() {
        const registerForm = document.getElementById('registerForm');
        if (!registerForm) return;

        registerForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const cellphone = document.getElementById('cellphone').value;
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;

            if (password !== confirmPassword) {
                alert('As senhas não coincidem');
                return;
            }

            const user = { name, email, cellphone, password };
            const response = await UserService.makeFetch('http://localhost:8080/gym/user', 'POST', user);

            if (response) {
                alert('Usuário registrado com sucesso! Faça login.');
                window.location.href = 'login.html';
            } else {
                alert('Erro ao registrar, tente novamente.');
            }
        });
    }

    static async makeFetch(url, method, body = {}) {
        try {
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(body)
            });

            if (!response.ok) throw new Error(`Erro: ${response.status}`);
            return await response.json();
        } catch (error) {
            console.error('Erro na requisição:', error);
            return null;
        }
    }
}

// Inicializando o serviço de usuário
document.addEventListener('DOMContentLoaded', UserService.registerUser);
