'use client';

import { useEffect, useState } from 'react';
import { addMeal, getQuestions } from '../../lib/api';
import { getToken } from '../../lib/token';
import { useRouter } from 'next/navigation';

export default function AddMealPage() {
    const router = useRouter();
    const [mealType, setMealType] = useState('lunch');
    const [questions, setQuestions] = useState<any[]>([]);
    const [answers, setAnswers] = useState<string[]>([]);
    const nowHour = new Date().getHours();
    const [mealHour, setMealHour] = useState(nowHour);

    useEffect(() => {
        const token = getToken();
        if (!token) return;
        getQuestions(mealType, token).then((qs) => {
            setQuestions(qs);
            setAnswers(qs.map(() => ''));
        });
    }, [mealType]);

    const handleAnswerChange = (i: number, val: string) => {
        const updated = [...answers];
        updated[i] = val;
        setAnswers(updated);
    };

    const handleSubmit = async () => {
        const token = getToken();
        if (!token) return;
        const payload = {
            mealType,
            mealDate: new Date().toLocaleDateString('en-GB').split('/').join('-'),
            mealHour: mealHour,
            answers: questions.map((q, i) => ({
                question: q.question,
                answer: answers[i],
            })),
        };
        await addMeal(token, payload);
        router.push('/dashboard');
    };

    return (
        <div className="p-6 space-y-4">
            <h1 className="text-xl font-bold">Add Meal</h1>
            <select
                value={mealType}
                onChange={(e) => setMealType(e.target.value)}
                className="border p-2 rounded"
            >
                <option value="lunch">Lunch</option>
                <option value="dinner">Dinner</option>
                <option value="breakfast">Breakfast</option>
            </select>

            <div className="mb-4">
                <label className="block font-medium mb-1">Hour of Meal (0–23)</label>
                <input
                    type="number"
                    min={0}
                    max={23}
                    value={mealHour}
                    onChange={(e) => setMealHour(parseInt(e.target.value))}
                    className="border p-2 rounded w-24"
                />
            </div>

            {questions.map((q, i) => (
                <div key={i} className="flex flex-col">
                    <label>{q.question}</label>
                    <input
                        className="border p-2 rounded"
                        value={answers[i] || ''}
                        onChange={(e) => handleAnswerChange(i, e.target.value)}
                    />
                </div>
            ))}

            <button
                onClick={handleSubmit}
                className="bg-green-600 text-white px-4 py-2 rounded"
            >
                Save Meal
            </button>
        </div>
    );
}
