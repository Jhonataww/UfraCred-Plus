import { api } from "src/boot/axios";
const RESOURCE = "/user";
export default {
  list(dados) {
    return api.get(RESOURCE, {
      params: {
        ...dados,
      },
    });
  },
  save(dados) {
    return api.post(RESOURCE, dados);
  },
  show(id) {
    return api.get(`${RESOURCE}/${id}`);
  },
  delete(id) {
    return api.delete(`${RESOURCE}/${id}`);
  },
  update(dados) {
    return api.put(`${RESOURCE}/${dados.id}`, dados);
  },
  alterarSenha(dados) {
    return api.post(`alterarSenha/${dados.id}`, dados);
  },
};
