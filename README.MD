# Imersão Fullcycle 18 - Sistema de Venda de Ingressos

![Imersão Full Stack && Full Cycle](https://events-fullcycle.s3.amazonaws.com/events-fullcycle/static/site/img/grupo_4417.png)

O evento foi desenvolvido durante a semana irmersãp full cycle usadno nodeJs com nest para o criação dos partners e Golang para a api de vendas. No entanto, eu decidi usar Java com Spring para a API de partner e nodeJs para api de vendas. O front-end foi desenvolvida usando nextjs.

- Para rodar a backend,

```
docker compose up
```

- Para rodar o front-end

```
cd nextjs-frontend
npm install
npm run dev
```

- O serviço Kong está disponível na porta 8000
- Os partners estão disponíveis na port 3000 e 3001
- A API de vendas estão disponíveis na porta 8080
- Os bancos estão disponíveis nas porta 3306 e 3307

![Home](https://github.com/engnandogabriel/imsersao-full-cycle/blob/main/images/home.png)
