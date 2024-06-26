import HttpResponse from './ResponseHttp';

export const badRequest = (error: any): HttpResponse => ({
  statusCode: 400,
  body: {
    type: 'BadRequest',
    name: error.name,
    message: error.message,
  },
});

export const unauthorized = (error: any): HttpResponse => ({
  statusCode: 401,
  body: {
    type: 'Unauthorized',
    name: error.name,
    message: error.message,
  },
});

export const unprocessableEntity = (error: any): HttpResponse => ({
  statusCode: 422,
  body: {
    type: 'UnprocessableEntity',
    name: error.name,
    message: error.message,
  },
});

export const notFound = (error: any): HttpResponse => ({
  statusCode: 404,
  body: {
    type: 'NotFound',
    name: error.name,
    message: error.message,
  },
});

export const serverError = (error: any): HttpResponse => ({
  statusCode: 500,
  body: {
    type: 'ServerError',
    name: error.name,
    message: error.message,
  },
});

export const success = (data: any): HttpResponse => ({
  statusCode: 200,
  body: {
    type: 'Success',
    message: data.message,
    data: data.data,
  },
});
