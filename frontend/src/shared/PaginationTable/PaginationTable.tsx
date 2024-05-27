import { ReactNode } from 'react'
import Table from '@/shared/Table/Table'
import { TableProps } from '@/shared/Table/TableTypes'

interface PaginationTableProps<Type, Key extends keyof Type>
	extends TableProps<Type, Key> {
	totalPages: number
	onNextPage: () => void
	onPrevPage: () => void
	currentPage: number
}

const PaginationTable = <Type, Key extends keyof Type>({
																												 data,
																												 columns,
																												 totalPages,
																												 onNextPage,
																												 onPrevPage,
																												 currentPage
																											 }: PaginationTableProps<Type, Key>): ReactNode => {
	return (
		<>
			<button
				disabled={currentPage === 1}
				onClick={onPrevPage}
			>
				Предыдущая страница
			</button>
			<button
				disabled={currentPage === totalPages}
				onClick={onNextPage}
			>
				Следующая страница
			</button>
			<p>Всего страниц: {totalPages}</p>
			<p>Текущая страница: {currentPage}</p>
			<Table data={data} columns={columns} />
		</>
	)
}

export default PaginationTable
