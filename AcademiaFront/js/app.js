// Função para adicionar o listener ao formulário de registro
document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerForm');

    if (registerForm) {
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

            try {
                const response = await fetch('http://localhost:8080/gym/user', { // Ajustar a URL para o backend correto
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        name: name,
                        email: email,
                        cellphone: cellphone,
                        password: password
                    }),
                });

                if (response.ok) {
                    alert('Usuário registrado com sucesso! Faça login.');
                    window.location.href = 'login.html';
                } else if (response.status === 409) {
                    alert('O email já está registrado. Tente outro.');
                } else {
                    alert('Erro ao registrar, tente novamente.');
                }
            } catch (error) {
                console.error('Erro ao registrar usuário:', error);
                alert('Erro no servidor, tente novamente.');
            }
        });
    }
});

// Função de login (caso esteja no mesmo arquivo)
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', async (event) => {
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
                    // Redireciona com base na role do usuário
                    if (data.role === 'ROLE_TRAINER') {
                        window.location.href = 'dashboard_trainer.html';
                    } else {
                        window.location.href = 'dashboard_user.html';
                    }
                } else {
                    alert('Falha no login, verifique suas credenciais.');
                }
            } catch (error) {
                console.error('Erro ao fazer login:', error);
                alert('Erro no servidor, tente novamente.');
            }
        });
    }
});

// Função para buscar os treinos (Dashboard geral)
async function fetchWorkouts() {
    try {
        const response = await fetch('http://localhost:8080/api/workouts');
        const workouts = await response.json();

        const workoutList = document.getElementById('workoutList');
        workoutList.innerHTML = ''; // Limpa a lista antes de renderizar

        workouts.forEach(workout => {
            const div = document.createElement('div');
            div.innerHTML = `<h3>${workout.name}</h3><p>${workout.description}</p>`;
            workoutList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao buscar treinos:', error);
    }
}

document.addEventListener('DOMContentLoaded', fetchWorkouts);

// Função para buscar os treinos de um usuário específico
async function fetchUserWorkouts() {
    try {
        const response = await fetch('http://localhost:8080/api/user/workouts');
        const workouts = await response.json();

        const workoutList = document.getElementById('workoutList');
        workoutList.innerHTML = ''; // Limpa a lista antes de renderizar

        workouts.forEach(workout => {
            const div = document.createElement('div');
            div.innerHTML = `<h3>${workout.name}</h3><p>${workout.description}</p>`;
            workoutList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao buscar treinos do usuário:', error);
    }
}

document.addEventListener('DOMContentLoaded', fetchUserWorkouts);

// Função para buscar os alunos e seus treinos (para treinador)
async function fetchStudents() {
    try {
        const response = await fetch('http://localhost:8080/api/trainer/students');
        const students = await response.json();

        const studentList = document.getElementById('studentList');
        studentList.innerHTML = ''; // Limpar a lista antes de renderizar

        students.forEach(student => {
            const div = document.createElement('div');
            div.innerHTML = `<h3>${student.name}</h3><button onclick="fetchWorkouts(${student.id})">Ver Treinos</button>`;
            studentList.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao buscar alunos:', error);
    }
}

document.addEventListener('DOMContentLoaded', fetchStudents);
