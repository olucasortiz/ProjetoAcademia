// dashboard_trainer.js

function fetchStudents() {
    fetch('http://localhost:8080/api/trainer/students')
    .then(response => response.json())
    .then(students => {
        const studentList = document.getElementById('studentList');
        studentList.innerHTML = '';  // Limpar a lista antes de renderizar

        students.forEach(student => {
            const div = document.createElement('div');
            div.innerHTML = `<h3>${student.name}</h3><button onclick="fetchWorkouts(${student.id})">Ver Treinos</button>`;
            studentList.appendChild(div);
        });
    })
    .catch(error => console.error('Erro ao buscar alunos:', error));
}

function fetchWorkouts(studentId) {
    fetch(`http://localhost:8080/api/trainer/students/${studentId}/workouts`)
    .then(response => response.json())
    .then(workouts => {
        const workoutList = document.getElementById('workoutList');
        workoutList.innerHTML = '';  // Limpar a lista antes de renderizar

        workouts.forEach(workout => {
            const div = document.createElement('div');
            div.innerHTML = `
                <h3>${workout.name}</h3>
                <p>${workout.description}</p>
                <button onclick="editWorkout(${workout.id})">Editar</button>
                <button onclick="deleteWorkout(${workout.id})">Deletar</button>
            `;
            workoutList.appendChild(div);
        });
    })
    .catch(error => console.error('Erro ao buscar treinos:', error));
}

document.addEventListener('DOMContentLoaded', fetchStudents);
