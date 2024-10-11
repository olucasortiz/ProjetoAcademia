// exercise.js
class ExerciseService {
    static async fetchExercises() {
        const exerciseList = document.getElementById('exerciseList');
        if (!exerciseList) return;

        const exercises = await ExerciseService.makeFetch('http://localhost:8080/gym/exercises', 'GET');

        if (exercises) {
            exerciseList.innerHTML = ''; // Limpar a lista antes de renderizar
            exercises.forEach(exercise => {
                const div = document.createElement('div');
                div.innerHTML = `<h3>${exercise.name}</h3><p>${exercise.description}</p>`;
                exerciseList.appendChild(div);
            });
        } else {
            console.error('Erro ao buscar exercícios');
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

// Inicializando o serviço de exercícios
document.addEventListener('DOMContentLoaded', ExerciseService.fetchExercises);
