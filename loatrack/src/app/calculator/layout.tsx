

export default function CalculatorLayout({
                                       children,
                                     }: Readonly<{
  children: React.ReactNode;
}>) {
  return (
      <main className="flex min-h-screen flex-col items-center p-24">
        <div>
          <h1 className="text-4xl font-bold mb-10">Next-Upgrade Calculator</h1>
        </div>
        {children}
      </main>
  );
}