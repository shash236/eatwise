import '../styles/globals.css';
import { ReactNode } from 'react';

export const metadata = {
  title: 'Eatwise',
  description: 'Track your meals easily',
};

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="en">
      <body className="bg-white text-gray-900">
        {children}
      </body>
    </html>
  );
}
