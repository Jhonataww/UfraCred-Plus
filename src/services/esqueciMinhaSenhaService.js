import { api } from "src/boot/axios";
const RESOURCE = "/esqueciMinhaSenha";
export default {
  solicitarTokenAcesso(email) {
    return api.get(RESOURCE, {
      params: {
        email,
      },
    });
  },
  alterarSenha(dados) {
    return api.post(RESOURCE, dados);
  },
};
