'use client';

import { useEffect, useState } from 'react';
import { getMeals } from '../../lib/api';
import { getToken } from '../../lib/token';
import Link from 'next/link';

export default function DashboardPage() {
    const [meals, setMeals] = useState<any[]>([]);
    const today = new Date().toLocaleDateString('en-GB').split('/').join('-'); // dd-MM-yyyy

    useEffect(() => {
        const token = getToken();
        if (!token) return;
        getMeals(token, today)
            .then((data) => {
                const sorted = [...data].sort((a, b) => a.mealHour - b.mealHour);
                setMeals(sorted);
            })
            .catch(() => alert('Failed to load meals'));
    }, []);


    return (
        <div className="p-6">
            <h1 className="text-xl font-bold mb-4">Meals for Today</h1>
            <Link href="/add-meal" className="text-blue-600 underline">
                + Add Meal
            </Link>
            <ul className="mt-4 space-y-4">
                {meals.map((meal, i) => (
                    <li key={i} className="border p-4 rounded">
                        <p><strong>{meal.mealType}</strong> ({meal.mealDate})</p>
                        {meal.answers.map((a: any, idx: number) => (
                            <p key={idx}><strong>{a.question}</strong>: {a.answer}</p>
                        ))}
                    </li>
                ))}
            </ul>
        </div>
    );
}
