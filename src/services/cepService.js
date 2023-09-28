import { api } from "src/boot/axios";
const RESOURCE = "/cep";
export default {
  list(dados) {
    return api.get(RESOURCE, {
      params: {
        cep : dados
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
};
