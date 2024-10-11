// workout.js
class WorkoutService {
    static async fetchWorkouts() {
        const workoutList = document.getElementById('workoutList');
        if (!workoutList) return;

        const workouts = await WorkoutService.makeFetch('http://localhost:8080/gym/workouts', 'GET');

        if (workouts) {
            workoutList.innerHTML = ''; // Limpar a lista antes de renderizar
            workouts.forEach(workout => {
                const div = document.createElement('div');
                div.innerHTML = `<h3>${workout.name}</h3><p>${workout.description}</p>`;
                workoutList.appendChild(div);
            });
        } else {
            console.error('Erro ao buscar treinos');
        }
    }

    static async addWorkout() {
        const workoutName = document.getElementById('workoutName').value;
        const workoutDescription = document.getElementById('workoutDescription').value;

        const workout = { name: workoutName, description: workoutDescription };
        const response = await WorkoutService.makeFetch('http://localhost:8080/gym/workouts', 'POST', workout);

        if (response) {
            alert('Treino adicionado com sucesso!');
            WorkoutService.fetchWorkouts();
        } else {
            alert('Erro ao adicionar treino.');
        }
    }

    static async makeFetch(url, method, body = {}) {
        try {
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: method === 'POST' ? JSON.stringify(body) : null
            });

            if (!response.ok) throw new Error(`Erro: ${response.status}`);
            return await response.json();
        } catch (error) {
            console.error('Erro na requisição:', error);
            return null;
        }
    }
}

// Inicializando o serviço de treinos
document.addEventListener('DOMContentLoaded', WorkoutService.fetchWorkouts);
