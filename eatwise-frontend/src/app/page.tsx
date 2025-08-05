'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { requestOtp } from '../lib/api';

export default function RequestOtpPage() {
  const [email, setEmail] = useState('');
  const router = useRouter();

  const handleSubmit = async () => {
    try {
      await requestOtp(email);
      router.push(`/verify-otp?email=${encodeURIComponent(email)}`);
    } catch {
      alert('Failed to send OTP.');
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-xl mb-4">Enter your email</h1>
      <input
        className="border p-2 rounded w-64"
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="email@example.com"
      />
      <button
        onClick={handleSubmit}
        className="mt-4 bg-blue-600 text-white px-4 py-2 rounded"
      >
        Request OTP
      </button>
    </div>
  );
}
