"use client"
import { createCategory } from '@/actions/categories-actions';
import Navbar from '@/components/navbar';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import Link from 'next/link';
import { useActionState } from 'react';

const initialState = {
    values: {
        name: "",
        icon: ""
    },
    errors: {
        name: "",
        icon: ""
    }
};

export default function CategoriesFormPage(){
    const [state, formAction, pendind] = useActionState(
        createCategory,
        initialState
    );
    return(
    <>
    <Navbar active="categorias"/>

    <main className="flex items-center justify-center">
        <div className=" bg-slate-900 rounded p-5 m-6 max-w-[500]">
            <h2 className="font-bold">Cadastrar Categoria</h2>
            <form action={formAction} className="space-y-4 mt-6">

                <div>
                    <Input name="name" placeholder="nome da categoria" aria-invalid={!!state?.errors.name}/>
                    <span className='text-sm text-destructive'>{state?.errors.name}</span>
                </div>

                <Input name="icon" placeholder="ícone"/>
                <div className="flex justify-around">
                    <Button variant="outline" asChild>
                        <Link href="/categories">Cancelar</Link>
                    </Button>
                    <Button>Salvar</Button>
                </div>
            </form>
        </div>
    </main>
    </>
    );
}