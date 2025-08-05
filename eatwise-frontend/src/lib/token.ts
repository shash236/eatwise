export const setToken = (token: string) => localStorage.setItem('eatwise_token', token);
export const getToken = () => localStorage.getItem('eatwise_token');
export const clearToken = () => localStorage.removeItem('eatwise_token');
