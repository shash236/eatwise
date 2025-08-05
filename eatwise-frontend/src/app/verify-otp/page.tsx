'use client';

import { useRouter, useSearchParams } from 'next/navigation';
import { useState } from 'react';
import { verifyOtp } from '../../lib/api';
import { setToken } from '../../lib/token';

export default function VerifyOtpPage() {
  const searchParams = useSearchParams();
  const email = searchParams.get('email') || '';
  const [otp, setOtp] = useState('');
  const router = useRouter();

  const handleVerify = async () => {
    try {
      const res = await verifyOtp(email, otp);
      setToken(res.jwtToken);
      router.push('/dashboard');
    } catch {
      alert('Invalid OTP.');
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-xl mb-4">Enter OTP sent to {email}</h1>
      <input
        className="border p-2 rounded w-64"
        value={otp}
        onChange={(e) => setOtp(e.target.value)}
        placeholder="Enter OTP"
      />
      <button
        onClick={handleVerify}
        className="mt-4 bg-green-600 text-white px-4 py-2 rounded"
      >
        Verify OTP
      </button>
    </div>
  );
}
