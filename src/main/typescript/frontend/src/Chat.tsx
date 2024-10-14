import React, { useState } from 'react';

// Define types for the API response and the component's state
interface ChatResponse {
    id: string,
    model: string,
    system_fingerprint: string,
    object: string,
    created: bigint,
    choices: ChatResponseChoice[],
}

interface ChatResponseChoice {
    finish_reason: string,
    index: bigint,
    message: ChatResponseMessage
}

interface ChatResponseMessage {
    role: string,
    content: string,
}

const Chat: React.FC = () => {
    const [input, setInput] = useState<string>(''); // Input field value as string
    const [response, setResponse] = useState<string>(''); // API response as string
    const [loading, setLoading] = useState<boolean>(false); // Loading state as boolean

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading(true);

        try {
            const res = await fetch('/api/chat/inference?user=' + JSON.stringify(input), {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }

            const data: ChatResponse = await res.json();
            console.log("Fetch response data:", data);
            setResponse(data.choices[0].message.content);
        } catch (error: any) {
            setResponse("Error: " + (error.message || 'Unknown error'));
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4">
            <h1 className="text-2xl font-bold mb-4">Chat with API</h1>

            <form onSubmit={handleSubmit} className="flex flex-col w-full max-w-md space-y-4">
                <input
                    type="text"
                    className="border border-gray-300 p-2 rounded-lg"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    placeholder="Ask something..."
                    required
                />
                <button type="submit" className="bg-blue-500 text-white p-2 rounded-lg">
                    {loading ? 'Loading...' : 'Send'}
                </button>
            </form>

            {response &&
                <div className="mt-4 p-4 bg-white border border-gray-300 rounded-lg shadow-md">
                    <h2 className="text-lg font-semibold">Response:</h2>
                    <p>{response}</p>
                </div>
            }
        </div>
    );
};

export default Chat;
