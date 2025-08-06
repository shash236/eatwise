const BASE = 'http://localhost:8080/api';

export const requestOtp = (email: string) =>
  fetch(`${BASE}/auth/request-otp`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email }),
  });

export const verifyOtp = async (email: string, otp: string) => {
  const res = await fetch(`${BASE}/auth/verify-otp`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, otp }),
  });

  if (!res.ok) {
    throw new Error('OTP verification failed');
  }

  return res.json(); // contains jwtToken
}


export const getQuestions = async (mealType: string, token: string) => {
  const res = await fetch(`${BASE}/questions?mealType=${mealType.toUpperCase()}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.json();
};

export const getMeals = async (token: string, date: string) => {
  const res = await fetch(`${BASE}/meals?date=${date}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.json();
};

export const addMeal = async (token: string, meal: any) => {
  const res = await fetch(`${BASE}/meals`, {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(meal),
  });
  return res.json();
};
