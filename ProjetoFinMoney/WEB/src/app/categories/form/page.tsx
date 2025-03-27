import Navbar from '@/components/navbar';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import Link from 'next/link';


export default async function Categoriespage(){

    return(
    <>
    <Navbar active="categorias"/>

    <main className="flex items-center justify-center">
        <div className=" bg-slate-900 rounded p-5 m-6 max-w-[500]">
            <h2 className="font-bold">Cadastrar Categoria</h2>

            <form action="" className="space-y-4 mt-6">
                <Input name="name" placeholder="nome da categoria"/>
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
    )
}