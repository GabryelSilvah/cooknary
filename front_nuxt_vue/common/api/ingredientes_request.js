export { listarIngredientes, byIdIngredientes, cadastrarIngredientes, alterarIngredientes, deletarIngredientes };
import Cookies from 'js-cookie';
const authorization = Cookies.get("token_auth");


const URL_BASE_API = "http://localhost:8081/ingredientes";

/*
*Não altere, adicione ou retire nada da estrutura, 
*código ou quaisquer outra coisa desse arquivo sem permissão
*/

//Listar
async function listarIngredientes() {

  const { data: responseAPI, error: errorAPI } = await useFetch(URL_BASE_API + "/listar", {
    headers: {
      "Content-Type": "application/json",
      "Authorization": 'Bearer ' + authorization
    }
  });

  return responseAPI;
}

//Buscar pelo ID
async function byIdIngredientes(id_ingred) {

  const { data: responseAPI, error: errorAPI } = await useFetch(URL_BASE_API + "/byId/" + id_ingred, {
    headers: {
      "Content-Type": "application/json",
      "Authorization": 'Bearer ' + authorization
    }
  });

  return responseAPI;
}

//Cadastrar
async function cadastrarIngredientes(corpo_request) {
  const { data: responseAPI, error: errorAPI } = await useFetch(URL_BASE_API + "/cadastrar",
    {
      method: "POST",
      headers: {
        "Content-Type": "appliingredion/json",
        "Authorization": 'Bearer ' + authorization
      },
      body: JSON.stringify(corpo_request)
    });

  return responseAPI;
}

//Alterar
async function alterarIngredientes(id_ingred, corpo_request) {
  const { data: responseAPI, error: errorAPI } = await useFetch(URL_BASE_API + "/alterar/" + id_ingred, {
    method: "PUT",
    headers: {
      "Content-Type": "appliingredion/json",
      "Authorization": 'Bearer ' + authorization
    },
    body: JSON.stringify(corpo_request)
  });;

  return responseAPI;
}

//Deletar
async function deletarIngredientes(id_ingred) {
  const { data: responseAPI, error: errorAPI } = await useFetch(URL_BASE_API + "/excluir/" + id_ingred, {
    headers: {
      "Content-Type": "application/json",
      "Authorization": 'Bearer ' + authorization
    }
  });

  return responseAPI;
}