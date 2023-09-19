import { Notify } from "quasar";

Notify.setDefaults({
  position: "bottom",
  timeout: 10000,
  textColor: "white",
  progress: true,
});

const DEFAULT_MSG_ERROR = "Ocorreu um erro. Contate o Suporte";
const DEFAULT_MSG_SUCCESS = "Operação realizada com sucesso.";

export default {
  alert(mensagem) {
    Notify.create({
      position: "bottom",
      message: mensagem,
      color: "yellow",
      textColor: "black",
      actions: [
        {
          icon: "close",
          color: "black",
          round: true,
          dense: true,
        },
      ],
    });
  },
  error(mensagem) {
    Notify.create({
      message: this.normalizeMessage(mensagem) || DEFAULT_MSG_ERROR,
      color: "negative",
      actions: [
        {
          icon: "close",
          color: "white",
          round: true,
          dense: true,
        },
      ],
    });
  },
  info(mensagem) {
    Notify.create({
      message: mensagem,
      color: "blue",
      actions: [
        {
          icon: "close",
          color: "white",
          round: true,
          dense: true,
        },
      ],
    });
  },
  multipleErrors(erroResponse) {
    let messages = [];
    if (erroResponse.hasOwnProperty("details")) {
      for (let index in erroResponse.details) {
        let formatMessage =
          erroResponse.details.length !== 1
            ? `<li>${erroResponse.details[index].message}</li>`
            : erroResponse.details[index].message;
        messages.push(formatMessage);
      }
    } else {
      messages.unshift(erroResponse.message);
    }
    Notify.create({
      html: true,
      message: `<ul>${messages.join("")}</ul>`,
      color: "negative",
      actions: [
        {
          icon: "close",
          color: "white",
          round: true,
          dense: true,
        },
      ],
    });
  },
  success(mensagem) {
    Notify.create({
      message: this.normalizeMessage(mensagem) || DEFAULT_MSG_SUCCESS,
      icon: "check",
      color: "positive",
      actions: [
        {
          icon: "close",
          color: "white",
          round: true,
          dense: true,
        },
      ],
    });
  },
  normalizeMessage(mensagem) {
    if (typeof mensagem == "object") {
      const { message } = mensagem;
      return message;
    } else if (typeof mensagem == "string") {
      return mensagem;
    }
    return "";
  },
};
