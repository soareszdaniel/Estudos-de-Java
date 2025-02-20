// Seleciona elementos do DOM
const formulario = document.querySelector("form"); // Formulário HTML
const Inome = document.querySelector(".nome");      // Campo de entrada do nome
const Iemail = document.querySelector(".email");    // Campo de entrada do email
const Itelefone = document.querySelector(".tel");  // Campo de entrada do telefone
const Isenha = document.querySelector(".senha");   // Campo de entrada da senha

// Função para cadastrar usuário
function cadastrar() {
    // Envia uma requisição POST para a API
    fetch('http://localhost:8080/cadastro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // Especifica o tipo de conteúdo como JSON
            'Accept': 'application/json'        // Aceita resposta em JSON
        },
        // Converte os dados do formulário para JSON
        body: JSON.stringify({
            nome: Inome.value,
            email: Iemail.value,
            telefone: Itelefone.value,
            senha: Isenha.value
        })
    })
    .then(function (res) { 
        console.log(res) // Log da resposta do servidor (sucesso)
    })
    .catch(function (res) { 
        console.log(res) // Log de erros (se houver falha na requisição)
    });
}

// Função para limpar os campos do formulário
function limpar() {
    Inome.value = "";
    Iemail.value = "";
    Itelefone.value = "";
    Isenha.value = "";
}

// Adiciona um listener para o evento de submit do formulário
formulario.addEventListener("submit", function (event) {
    event.preventDefault(); // Impede o comportamento padrão de recarregar a página
    
    cadastrar(); // Chama a função de cadastro
    limpar();    // Chama a função de limpeza após o cadastro
});