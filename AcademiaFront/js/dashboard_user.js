// dashboard_user.js

function fetchUserWorkouts() {
    fetch('http://localhost:8080/api/user/workouts')  // Endpoint para buscar treinos do usuário logado
    .then(response => response.json())
    .then(workouts => {
        const workoutList = document.getElementById('workoutList');
        workoutList.innerHTML = '';  // Limpar a lista antes de renderizar

        workouts.forEach(workout => {
            const div = document.createElement('div');
            div.innerHTML = `<h3>${workout.name}</h3><p>${workout.description}</p>`;
            workoutList.appendChild(div);
        });
    })
    .catch(error => console.error('Erro ao buscar treinos:', error));
}

document.addEventListener('DOMContentLoaded', fetchUserWorkouts);
